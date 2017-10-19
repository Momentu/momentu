package com.momentu.momentuapi.security.auth.jwt;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SkipPathRequestMatcher returns true if request matches processingPath.
 * Can be used by AbstractAuthenticationProcessingFilter to determine if authentication is required.
 */
public class SkipPathRequestMatcher implements RequestMatcher {
    private OrRequestMatcher skipPathMatchers;
    private RequestMatcher pathMatcher;

    public SkipPathRequestMatcher(List<String> pathsToSkip, String processingPath) {
        if(pathsToSkip == null) {
            throw new IllegalArgumentException("PathsToSkip list not assigned");
        }
        List<RequestMatcher> skipPathList =
                pathsToSkip.stream()
                        .map(path -> new AntPathRequestMatcher(path))
                        .collect(Collectors.toList());
        this.skipPathMatchers = new OrRequestMatcher(skipPathList);
        pathMatcher = new AntPathRequestMatcher(processingPath);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        if(skipPathMatchers.matches(request)) {
            return false;
        }
        return pathMatcher.matches(request);
    }
}
