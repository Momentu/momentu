package com.momentu.momentuapi.security.model.token;

import com.momentu.momentuapi.security.exceptions.JwtExpiredTokenException;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.BadCredentialsException;

public class RawAccessJwtToken implements JwtToken {
    private String token;

    public RawAccessJwtToken(String token) {
        this.token = token;
    }

    public Jws<Claims> parseClaims(String signingKey) {
        try {
            return Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(this.token);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            throw new BadCredentialsException("Invalid JWT Token: ", ex);
        } catch (ExpiredJwtException expiredException) {
            throw new JwtExpiredTokenException(this, "JWT Token expired: ", expiredException);
        }
    }

    @Override
    public String getToken() {
        return null;
    }
}
