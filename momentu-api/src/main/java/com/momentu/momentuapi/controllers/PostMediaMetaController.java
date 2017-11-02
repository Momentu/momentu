package com.momentu.momentuapi.controllers;

import com.momentu.momentuapi.entities.*;
import com.momentu.momentuapi.models.HashtagAndLocation;
import com.momentu.momentuapi.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RepositoryRestController
@RequestMapping("/api")
public class PostMediaMetaController {

    @Autowired
    private MediaMetaRepository mediaMetaRepository;

    @Autowired
    private HashtagRepository hashtagRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/mediameta", method = RequestMethod.POST)
    public ResponseEntity<PersistentEntityResource> postMediaMeta(@RequestBody HashtagAndLocation hashtagAndLocation,
                                                                  @RequestHeader HttpHeaders headers,
                                                                  PersistentEntityResourceAssembler persistentEntityResourceAssembler)
    {
        // Default to user jsmith040 for now
        // TODO: update to use token username or store/retrieve id from token
        Optional<User> existingUser = userRepository.findByUsername("jsmith040");
        if(existingUser.equals(Optional.empty())) {
            throw new IllegalArgumentException("User does not exist");
        }

        Location locationWithId = null;
        Optional<Location> optionalLocation = locationRepository
                .findByCityAndState(hashtagAndLocation.getCity(), hashtagAndLocation.getState());
        if(optionalLocation.equals(Optional.empty())) {
            // TODO: create location, assign to locationWithId, and store
        }
        else {
            locationWithId = optionalLocation.get();
        }

        Hashtag hashtagWithId = null;
        Optional<Hashtag> optionalHashtag = hashtagRepository
                .findByLabelAndLocationId(hashtagAndLocation.getHashtagLabel(), locationWithId.getId());
        if(optionalHashtag.equals(Optional.empty())) {
            // TODO: create hashtag, assign to hastagWithId, and store
        }
        else {
            hashtagWithId = optionalHashtag.get();
        }

        return null;
    }
}