package com.momentu.momentuapi.service.impl;

import com.momentu.momentuapi.entities.PasswordResetRequest;
import com.momentu.momentuapi.entities.User;
import com.momentu.momentuapi.repos.PasswordResetRequestRepository;
import com.momentu.momentuapi.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
public class UserSecurityServiceImpl implements UserSecurityService {

    @Autowired
    private PasswordResetRequestRepository passwordResetRequestRepository;

    @Override
    public boolean validPasswordResetToken(String resetToken) {
        String hashedToken = this.hashToken(resetToken);

        Optional<PasswordResetRequest> fetchedPasswordResetRequest = passwordResetRequestRepository.findByHashedToken(hashedToken);
        if(fetchedPasswordResetRequest.equals(Optional.empty())) {
            return false;
        }
        PasswordResetRequest passwordResetRequest = fetchedPasswordResetRequest.get();

        Date date = new Date();
        if(!date.before(passwordResetRequest.getExpiryDate())) {
            return false;
        }

        return true;
    }

    boolean validPassword(User user, String currentPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        bCryptPasswordEncoder.encode(currentPassword);
        if(!bCryptPasswordEncoder.matches(currentPassword, user.getPassword())) {
            return false;
        }
        return true;
    }

    @Override
    public String hashToken(String resetToken) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch(NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("SHA-256 algorithm not found somehow");
        }
        messageDigest.update(resetToken.getBytes());
        String hashedToken = new String(messageDigest.digest());
        return hashedToken;
    }
}
