package com.momentu.momentuapi.controllers;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.momentu.momentuapi.entities.*;
import com.momentu.momentuapi.entities.keys.HashtagKey;
import com.momentu.momentuapi.models.HashtagAndLocation;
import com.momentu.momentuapi.repos.*;
import com.momentu.momentuapi.security.auth.jwt.extractor.ClaimExtractor;
import com.momentu.momentuapi.storage.config.S3Settings;
import com.momentu.momentuapi.storage.key.S3KeyGenerator;
import com.momentu.momentuapi.storage.s3.S3Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.Principal;
import java.util.*;

@RepositoryRestController
@RequestMapping("/")
public class MediaController {
    private final ClaimExtractor claimExtractor;
    private final MediaMetaRepository mediaMetaRepository;
    private final HashtagRepository hashtagRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final MediaCommentRepository commentRepository;
    private final S3Manager s3Manager;
    private final S3Settings s3Settings;
    private final S3KeyGenerator s3KeyGenerator;

    @Value("${aws.s3.cloudfront.image.protocol}")
    private String imageProtocol;

    @Value("${aws.s3.cloudfront.image.location}")
    private String imageLocation;

    @Value("${aws.s3.cloudfront.imageresized.location}")
    private String thumbnailLocation;

    @Autowired
    MediaController(ClaimExtractor claimExtractor, MediaMetaRepository mediaMetaRepository, HashtagRepository hashtagRepository,
                    LocationRepository locationRepository, UserRepository userRepository, MediaCommentRepository commentRepository,
                    S3Manager s3Manager, S3Settings s3Settings, S3KeyGenerator s3KeyGenerator) {
        this.claimExtractor = claimExtractor;
        this.mediaMetaRepository = mediaMetaRepository;
        this.hashtagRepository = hashtagRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
        this.s3Manager = s3Manager;
        this.s3Settings = s3Settings;
        this.s3KeyGenerator = s3KeyGenerator;
        this.commentRepository = commentRepository;
    }

    @RequestMapping(value = "/media_upload", method = RequestMethod.POST)
    public @ResponseBody Map uploadMedia(@RequestHeader HttpHeaders headers, @RequestParam("file") MultipartFile file,
                                         @RequestParam("hashtagLabel") String hashtagLabel, @RequestParam("city") String city,
                                         @RequestParam("state") String state,
                                         @RequestParam(value = "mediaType", required = false) String mediaType) {
        HashtagAndLocation hashtagAndLocation = new HashtagAndLocation(hashtagLabel, city, state);
        Map<String, String> response = new HashMap<>();
        InputStream inputStream = null;

        if(hashtagAndLocation.isValid() == false) {
            throw new IllegalArgumentException("Invalid data");
        }
        if (!file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                String fileExtension = "";
                if(fileName.indexOf(".") > 0) {
                    fileExtension = fileName.substring(fileName.lastIndexOf("."));
                }
                byte[] fileBytes = file.getBytes();
                Long contentLength = new Long(fileBytes.length);
                inputStream = new ByteArrayInputStream(fileBytes);
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentLength(contentLength);
                String keyName = s3KeyGenerator.getUniqueKey().concat(fileExtension);

                AWSCredentials awsCredentials = new BasicAWSCredentials(s3Settings.getAccessKeyId(), s3Settings.getSecretAccessKey());
                s3Manager.upload(awsCredentials, s3Settings.getMediaBucketName(), keyName, inputStream, objectMetadata);

                String username = claimExtractor.extractUsername(headers);
                User user = null;
                Optional<User> existingUser = userRepository.findByUsername(username);
                if(existingUser.equals(Optional.empty())) {
                    throw new IllegalArgumentException("User does not exist");
                }
                else {
                    user = existingUser.get();
                }

                Location locationWithId = null;
                Optional<Location> optionalLocation = locationRepository
                        .findByStateCity(hashtagAndLocation.getCity(), hashtagAndLocation.getState());
                if(optionalLocation.equals(Optional.empty())) {
                    Location location = new Location(hashtagAndLocation.getCity(), hashtagAndLocation.getState());
                    locationWithId = locationRepository.save(location);
                }
                else {
                    locationWithId = optionalLocation.get();
                }

                Hashtag hashtagWithId = null;
                Optional<Hashtag> optionalHashtag = hashtagRepository
                        .findByLabelAndLocationId(hashtagAndLocation.getHashtagLabel(), locationWithId.getId());
                if(optionalHashtag.equals(Optional.empty())) {
                    HashtagKey hashtagKey = new HashtagKey();
                    hashtagKey.setLocation(locationWithId);
                    hashtagKey.setLabel(hashtagAndLocation.getHashtagLabel());
                    Hashtag hashtag = new Hashtag();
                    hashtag.setHashtagKey(hashtagKey);
                    hashtag.setCount(1L);

                    hashtagWithId = hashtagRepository.save(hashtag);
                }
                else {
                    //TODO: find safer way to increment
                    hashtagWithId = optionalHashtag.get();
                    hashtagWithId.setCount(hashtagWithId.getCount() + 1);
                    hashtagWithId = hashtagRepository.save(hashtagWithId);
                }

                String imageUrl = imageProtocol + "://" + imageLocation + "/" + keyName;
                String thumbnailUrl = imageProtocol + "://" + thumbnailLocation + "/" + keyName;

                MediaMeta mediaMeta = new MediaMeta();
                mediaMeta.setUser(user);
                mediaMeta.setCreated(new Date());
                mediaMeta.setRemoved(false);
                mediaMeta.setHashtagLabel(hashtagAndLocation.getHashtagLabel());
                mediaMeta.setLocation(locationWithId);
                mediaMeta.setImageLocation(imageUrl);
                mediaMeta.setThumbnailLocation(thumbnailUrl);
                if(mediaType != null) {
                    mediaMeta.setMediaType(mediaType);
                } else {
                    //TODO: Remove whenever app is ready. Defaults to "image" for now
                    mediaMeta.setMediaType("image");
                }
                mediaMeta = mediaMetaRepository.save(mediaMeta);
                response.put("status", "success");
                response.put("mediaMetaId", mediaMeta.getId().toString());
                return response;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.put("status", "fail");
        return response;
    }

    @RequestMapping(value = "/mediaLike", method = RequestMethod.POST)
    public @ResponseBody Map mediaLike(@RequestParam Long mediaMetaId, Principal principal) {
        User currentUser;
        MediaMeta currentMediaMeta;
        Optional<User> user = userRepository.getUserByPrincipal(principal);
        Optional<MediaMeta> mediaMeta = mediaMetaRepository.findById(mediaMetaId);

        if(user.equals(Optional.empty())) {
            throw new IllegalArgumentException("User does not exist");
        }
        if(mediaMeta.equals(Optional.empty())) {
            throw new IllegalArgumentException("Media Meta does not exist");
        }

        currentMediaMeta = mediaMeta.get();
        currentUser = user.get();
        currentMediaMeta.getUserLikes().add(currentUser);
        mediaMetaRepository.save(currentMediaMeta);
        return Collections.singletonMap("status", "true");
    }

    @RequestMapping(value = "/mediaUnlike", method = RequestMethod.POST)
    public @ResponseBody Map mediaUnlike(@RequestParam Long mediaMetaId, Principal principal) {
        User currentUser;
        MediaMeta currentMediaMeta;
        Optional<User> user = userRepository.getUserByPrincipal(principal);
        Optional<MediaMeta> mediaMeta = mediaMetaRepository.findById(mediaMetaId);

        if(user.equals(Optional.empty())) {
            throw new IllegalArgumentException("User does not exist");
        }
        if(mediaMeta.equals(Optional.empty())) {
            throw new IllegalArgumentException("Media Meta does not exist");
        }

        currentMediaMeta = mediaMeta.get();
        currentUser = user.get();

        Boolean success = currentMediaMeta.getUserLikes().remove(currentUser);
        if(!success) {
            throw new IllegalArgumentException("No like exists for Media Meta");
        }
        mediaMetaRepository.save(currentMediaMeta);
        return Collections.singletonMap("status", "true");
    }

    @RequestMapping(value = "/mediaDelete", method = RequestMethod.POST)
    public @ResponseBody Map mediaDelete(@RequestParam Long mediaMetaId, Principal principal) {
        User currentUser;
        MediaMeta currentMediaMeta;
        Optional<User> user = userRepository.getUserByPrincipal(principal);
        Optional<MediaMeta> mediaMeta = mediaMetaRepository.findById(mediaMetaId);

        if(user.equals(Optional.empty())) {
            throw new IllegalArgumentException("User does not exist");
        }
        if(mediaMeta.equals(Optional.empty())) {
            throw new IllegalArgumentException("Media Meta does not exist");
        }

        currentMediaMeta = mediaMeta.get();
        currentUser = user.get();
        User mediaMetaUser = currentMediaMeta.getUser();
        if(currentUser.getId() != mediaMetaUser.getId()) {
            throw new IllegalArgumentException("User does not own Media Meta. Cannot delete");
        }

        currentMediaMeta.setRemoved(true);
        mediaMetaRepository.save(currentMediaMeta);
        return Collections.singletonMap("status", "true");
    }

    @RequestMapping(value = "/postComment", method = RequestMethod.POST)
    public @ResponseBody Map postComment(@RequestParam Long mediaMetaId, @RequestParam String comment,
                                          Principal principal) {
        User currentUser;
        MediaMeta currentMediaMeta;
        MediaComment mediaComment;

        if(comment == null) {
            throw new IllegalArgumentException("comment key needs a value");
        }
        if(mediaMetaId == null) {
            throw new IllegalArgumentException("mediaMetaId key needs a value");
        }

        Optional<User> user = userRepository.getUserByPrincipal(principal);
        Optional<MediaMeta> mediaMeta = mediaMetaRepository.findById(mediaMetaId);
        if(user.equals(Optional.empty())) {
            throw new IllegalArgumentException("User does not exist");
        }
        if(mediaMeta.equals(Optional.empty())) {
            throw new IllegalArgumentException("Media Meta does not exist");
        }

        currentMediaMeta = mediaMeta.get();
        currentUser = user.get();
        mediaComment = new MediaComment();
        mediaComment.setComment(comment);
        mediaComment.setCreatedDate(new Date());
        mediaComment.setUser(currentUser);
        mediaComment.setMediaMeta(currentMediaMeta);
        commentRepository.save(mediaComment);

        return Collections.singletonMap("status", "true");
    }
}