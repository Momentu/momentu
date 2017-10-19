package com.momentu.momentuapi.security.auth.json;

import com.momentu.momentuapi.entities.User;
import com.momentu.momentuapi.security.model.UserContext;
import com.momentu.momentuapi.service.DatabaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JsonAuthenticationProvider implements AuthenticationProvider {
    private final BCryptPasswordEncoder passwordEncoder;
    private final DatabaseUserService userService;

    @Autowired
    public JsonAuthenticationProvider(final DatabaseUserService userService, final BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        if(authentication == null) {
            throw new IllegalArgumentException("No authentication data provided");
        }

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = userService.getByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid");
        }

        if(user.getRoles() == null) {
            throw new InsufficientAuthenticationException("User has no roles assigned");
        }

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getUsername(), authorities);

        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
