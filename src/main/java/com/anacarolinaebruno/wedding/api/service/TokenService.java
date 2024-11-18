package com.anacarolinaebruno.wedding.api.service;

import com.anacarolinaebruno.wedding.api.model.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${application.jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("API Wedding")
                .withSubject(user.getLogin())
                .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                .sign(algorithm);
    }

    public String getSubject(String tokenJWT) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.require(algorithm)
                .withIssuer("API Wedding")
                .build()
                .verify(tokenJWT)
                .getSubject();
    }
}
