package com.momentu.momentuapi.security.auth.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.momentu.momentuapi.common.ErrorCode;
import com.momentu.momentuapi.common.ErrorResponse;
import com.momentu.momentuapi.security.exceptions.AuthMethodNotSupportedException;
import com.momentu.momentuapi.security.exceptions.JwtExpiredTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JsonAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper;

    @Autowired
    JsonAuthenticationFailureHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        if (exception instanceof BadCredentialsException) {
            objectMapper.writeValue(response.getWriter(),
                    ErrorResponse.of("Invalid Username or Password", ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
        } else if (exception instanceof JwtExpiredTokenException) {
            objectMapper.writeValue(response.getWriter(),
                    ErrorResponse.of("Token has expired", ErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED));
        } else if (exception instanceof AuthMethodNotSupportedException) {
            objectMapper.writeValue(response.getWriter(),
                    ErrorResponse.of(exception.getMessage(), ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
        }

        objectMapper.writeValue(response.getWriter(),
                ErrorResponse.of("Authentication failed", ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
    }
}
