package com.momentu.momentuapi.security.exceptions;

import com.momentu.momentuapi.security.model.token.JwtToken;
import org.springframework.security.core.AuthenticationException;

public class JwtExpiredTokenException extends AuthenticationException {

    private JwtToken token;

    public JwtExpiredTokenException(JwtToken token, String msg, Throwable t) {
        super(msg, t);
        this.token = token;
    }

    public JwtExpiredTokenException(String msg) {
        super(msg);
    }

    public String token() {
        return this.token.getToken();
    }
}
