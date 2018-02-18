package com.momentu.momentuapi.repos.impl;

import com.momentu.momentuapi.entities.User;
import com.momentu.momentuapi.repos.UserRepository;
import com.momentu.momentuapi.repos.UserRepositoryCustom;
import com.momentu.momentuapi.security.auth.jwt.JwtAuthenticationToken;
import com.momentu.momentuapi.security.model.UserContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> getUserByPrincipal(Principal principal) {
        JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) principal;
        UserContext userContext = (UserContext) jwtToken.getPrincipal();
        String username = userContext.getUsername();
        Optional<User> user = userRepository.findByUsername(username);
        return user;
    }
}
