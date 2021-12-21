package org.martikan.mastore.userapi.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.martikan.mastore.userapi.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class JwtUtils {

    @Value("${token.expiration}")
    private String expirationTime;

    @Value("${token.secret}")
    private String secret;

    public String generateToken(final UserDTO userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(Objects.requireNonNull(expirationTime))))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

}
