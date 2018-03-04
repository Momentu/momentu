package com.momentu.momentuapi.controllers;

import com.momentu.momentuapi.emailer.AmazonSESEmailer;
import com.momentu.momentuapi.emailer.models.EmailMessage;
import com.momentu.momentuapi.entities.Role;
import com.momentu.momentuapi.entities.User;
import com.momentu.momentuapi.entities.UserRole;
import com.momentu.momentuapi.repos.UserRepository;
import com.momentu.momentuapi.repos.UserRoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RepositoryRestController
@RequestMapping(name="/")
public class RegisterUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private AmazonSESEmailer amazonSESEmailer;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<PersistentEntityResource> register(@RequestBody User user
                                                            ,PersistentEntityResourceAssembler persistentEntityResourceAssembler)
    {

        if(StringUtils.isBlank(user.getUsername())) {
            throw new IllegalArgumentException("Missing value for registration");
        }

        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if(!existingUser.equals(Optional.empty())) {
            throw new IllegalArgumentException("User already exists1");
        }

        existingUser = userRepository.findByEmail(user.getEmail());
        if(!existingUser.equals(Optional.empty())) {
            throw new IllegalArgumentException("User already exists2");
        }

        User newUser = userRepository.save(user);
        UserRole roleAssociation = new UserRole(user.getId(), Role.MEMBER);
        userRoleRepository.save(roleAssociation);

        return ResponseEntity.ok(persistentEntityResourceAssembler.toResource(user));
    }

    @RequestMapping(value = "forgotPassword", method = RequestMethod.POST)
    public @ResponseBody Map forgotPassword() {
        //TODO: Complete Password Reset Functionality
        EmailMessage emailMessage = new EmailMessage("no-reply@momentu.xyz", "", "someSubject", "htmlBody", "textBody");
        boolean status  = amazonSESEmailer.sendEmail(emailMessage);
        Map<String, String> response = new HashMap<>();
        response.put("success", Boolean.toString(status));
        return response;
    }
}