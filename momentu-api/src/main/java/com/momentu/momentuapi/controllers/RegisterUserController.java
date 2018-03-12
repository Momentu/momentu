package com.momentu.momentuapi.controllers;

import com.momentu.momentuapi.emailer.AmazonSESEmailer;
import com.momentu.momentuapi.emailer.models.EmailMessage;
import com.momentu.momentuapi.emailer.models.templates.PasswordResetEmailMessage;
import com.momentu.momentuapi.entities.PasswordResetRequest;
import com.momentu.momentuapi.entities.Role;
import com.momentu.momentuapi.entities.User;
import com.momentu.momentuapi.entities.UserRole;
import com.momentu.momentuapi.repos.PasswordResetRequestRepository;
import com.momentu.momentuapi.repos.UserRepository;
import com.momentu.momentuapi.repos.UserRoleRepository;
import com.momentu.momentuapi.service.UserSecurityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RepositoryRestController
@RequestMapping(name="/")
public class RegisterUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private AmazonSESEmailer amazonSESEmailer;

    @Autowired
    private PasswordResetRequestRepository passwordResetRequestRepository;

    @Autowired
    private UserSecurityService userSecurityService;

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
    public @ResponseBody Map forgotPassword(@RequestParam String emailAddress) {
        Optional<User> existingUser = userRepository.findByEmail(emailAddress);
        if(existingUser.equals(Optional.empty())) {
            return Collections.singletonMap("status", "true");
        }
        User user = existingUser.get();

        String resetToken = UUID.randomUUID().toString();
        String hashedToken = userSecurityService.hashToken(resetToken);
        PasswordResetRequest passwordResetRequest = new PasswordResetRequest(user, hashedToken);
        passwordResetRequestRepository.save(passwordResetRequest);

        String link = "http://momentu/" + resetToken;
        EmailMessage emailMessage = new PasswordResetEmailMessage("no-reply@momentu.xyz", emailAddress, link);
        amazonSESEmailer.sendEmail(emailMessage);
        return Collections.singletonMap("status", "true");
    }

    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public @ResponseBody Map changePassword(@RequestParam String resetToken, @RequestParam String currentPassword,
                                            @RequestParam String newPassword) {
        String hashedToken = userSecurityService.hashToken(resetToken);
        Optional<PasswordResetRequest> fetchedPasswordResetRequest = passwordResetRequestRepository.findByHashedToken(hashedToken);
        if(fetchedPasswordResetRequest.equals(Optional.empty())) {
            return Collections.singletonMap("status", "true");
        }
        PasswordResetRequest passwordResetRequest = fetchedPasswordResetRequest.get();
        if(userSecurityService.validPasswordResetToken(resetToken, currentPassword)) {
            User currentUser = passwordResetRequest.getUser();
            currentUser.setPassword(newPassword);
            userRepository.save(currentUser);
        }
        return Collections.singletonMap("status", "true");
    }
}