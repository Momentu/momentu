package com.momentu.momentuapi.controllers;

import com.momentu.momentuapi.entities.*;
import com.momentu.momentuapi.entities.keys.HashtagKey;
import com.momentu.momentuapi.models.HashtagAndLocation;
import com.momentu.momentuapi.repos.*;
import com.momentu.momentuapi.security.auth.jwt.extractor.ClaimExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RepositoryRestController
@RequestMapping("/")
public class PostMediaMetaController {
    private final ClaimExtractor claimExtractor;
    private final MediaMetaRepository mediaMetaRepository;
    private final HashtagRepository hashtagRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    @Autowired
    PostMediaMetaController(ClaimExtractor claimExtractor, MediaMetaRepository mediaMetaRepository, HashtagRepository hashtagRepository,
                            LocationRepository locationRepository, UserRepository userRepository) {
        this.claimExtractor = claimExtractor;
        this.mediaMetaRepository = mediaMetaRepository;
        this.hashtagRepository = hashtagRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/media", method = RequestMethod.POST)
    public ResponseEntity<PersistentEntityResource> postMediaMeta(@RequestBody HashtagAndLocation hashtagAndLocation,
                                                                  @RequestHeader HttpHeaders headers,
                                                                  PersistentEntityResourceAssembler persistentEntityResourceAssembler)
    {
        if(hashtagAndLocation.isValid() == false) {
            throw new IllegalArgumentException("Invalid data");
        }

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

//        MediaMeta mediaMeta = new MediaMeta(user.getId(), hashtagWithId.getHashtagKey().getLabel(), locationWithId.getId());
        MediaMeta mediaMeta = new MediaMeta();
        mediaMeta.setUserId(user.getId());
        mediaMeta.setCreated(new Date());
        mediaMeta.setRemoved(false);
        mediaMeta.setHashtagLabel(hashtagAndLocation.getHashtagLabel());
        mediaMeta.setLocation(locationWithId);
        mediaMeta = mediaMetaRepository.save(mediaMeta);
        return ResponseEntity.ok(persistentEntityResourceAssembler.toResource(mediaMeta));
    }
}