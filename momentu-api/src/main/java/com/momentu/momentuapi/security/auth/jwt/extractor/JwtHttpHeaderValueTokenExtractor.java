package com.momentu.momentuapi.security.auth.jwt.extractor;

import com.momentu.momentuapi.security.config.JwtSettings;
import com.momentu.momentuapi.security.model.token.RawAccessJwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtHttpHeaderValueTokenExtractor implements TokenExtractor, ClaimExtractor {
    public static String HEADER_PREFIX = "Bearer ";
    private final JwtSettings jwtSettings;

    @Autowired
    JwtHttpHeaderValueTokenExtractor(JwtSettings jwtSettings) {
        this.jwtSettings = jwtSettings;
    }

    @Override
    public String extract(String httpHeaderValue) {
        if(StringUtils.isBlank(httpHeaderValue)) {
            throw new AuthenticationServiceException("Authentication HTTP Header cannot be blank");
        }

        if(httpHeaderValue.length() < HEADER_PREFIX.length()) {
            throw new AuthenticationServiceException("Authentication HTTP Header Value missing characters");
        }

        return httpHeaderValue.substring(HEADER_PREFIX.length(), httpHeaderValue.length());
    }

    public String extractUsername(HttpHeaders headers) {
        Optional<String> headerToken = headers.get("Authorization").stream().findFirst();
        if(headerToken.equals(Optional.empty())) {
            throw new IllegalArgumentException("No Authorization Header Token");
        }
        String token = extract(headerToken.get());
        RawAccessJwtToken rawToken = new RawAccessJwtToken(token);
        Jws<Claims> claims = rawToken.parseClaims(jwtSettings.getTokenSigningKey());
        String username = claims.getBody().getSubject();
        return username;
    }
}
