package com.momentu.momentuapi.security.auth.jwt;

import com.momentu.momentuapi.security.model.UserContext;
import com.momentu.momentuapi.security.model.token.RawAccessJwtToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private UserContext userContext;
    private RawAccessJwtToken rawAccessJwtToken;

    public JwtAuthenticationToken(RawAccessJwtToken unsafeToken) {
        super(null);
        this.rawAccessJwtToken = unsafeToken;
        this.setAuthenticated(false);
    }

    public JwtAuthenticationToken(UserContext userContext, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.eraseCredentials();
        this.userContext = userContext;
        super.setAuthenticated(true);
    }

    /**
     * Called with false when constructed with unsafeToken, otherwise super.setAuthenticated(...) is used
     */
    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException(
                    "Cannot set token to trusted. Use constructor taking GrantedAuthority collection instead.");
        }
        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return this.rawAccessJwtToken;
    }

    @Override
    public Object getPrincipal() {
        return this.userContext;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.rawAccessJwtToken = null;
    }
}
