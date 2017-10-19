package com.momentu.momentuapi.controllers;

import com.momentu.momentuapi.entities.User;
import com.momentu.momentuapi.repos.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RepositoryRestController
@RequestMapping("/api")
public class RegisterUserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<PersistentEntityResource> checkIn(@RequestBody User user,
                                                            PersistentEntityResourceAssembler persistentEntityResourceAssembler) {

        if(StringUtils.isBlank(user.getUsername())) {
            throw new IllegalArgumentException("Missing value for registration");
        }

        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if(!existingUser.isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        existingUser = userRepository.findByEmail(user.getEmail());
        if(!existingUser.isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        return ResponseEntity.ok(persistentEntityResourceAssembler.toResource(user));

    }
}
