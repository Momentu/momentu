package com.momentu.momentuapi.security.auth.jwt.extractor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

@Component
public class JwtHttpHeaderValueTokenExtractor implements TokenExtractor {
    public static String HEADER_PREFIX = "Bearer ";

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
}
