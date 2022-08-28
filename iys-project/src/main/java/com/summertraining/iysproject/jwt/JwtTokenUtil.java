package com.summertraining.iysproject.jwt;

import com.summertraining.iysproject.model.Person;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Generate JWT, aka access token, based on user's credentials
 */
@Component
public class JwtTokenUtil {
    private static final long EXPIRE_DURATION = 1 * 60 * 60 * 1000; // 1 hr
    private static final Logger LOG = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${app.jwt.secret}")
    private String secretKey;

    /**
     * Generate the JWT by providing the token's specifications, as issuer, duration, etc.
     * @param person person values to be used for token
     * @return JSON Web Token
     */
    public String generateAccessToken(Person person) {
        return Jwts.builder()
                .setSubject(person.getId() + ", " + person.getUsername())
                .claim("roles", person.getPersonRole().toString()) // set a role claim to a person/user
                .setIssuer(person.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact(); // functionality: A compact URL-safe JWT string.
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            return true;
        }
        catch (ExpiredJwtException e) {
            LOG.error("JWT expired", e);
        }
        catch (IllegalArgumentException e) {
            LOG.error("Token field is not appropriate: null, empty, or whitespace only", e);
        }
        catch (MalformedJwtException e) {
            LOG.error("JWT is invalid", e);
        }
        catch (UnsupportedJwtException e) {
            LOG.error("JWT is not supported", e);
        }
        catch (SignatureException e) {
            LOG.error("Signature invalid", e);
        }

        return false;
    }

    /**
     * gets the value of the subject field of a given token.
     * The subject contains User ID and email,
     * which will be used to recreate a User object.
     */
    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
