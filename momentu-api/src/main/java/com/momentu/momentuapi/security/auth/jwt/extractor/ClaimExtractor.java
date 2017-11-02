package com.momentu.momentuapi.security.auth.jwt.extractor;

import org.springframework.http.HttpHeaders;

public interface ClaimExtractor {
    String extractUsername(HttpHeaders headers);
}
