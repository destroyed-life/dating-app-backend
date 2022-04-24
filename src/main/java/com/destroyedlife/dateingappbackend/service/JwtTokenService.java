package com.destroyedlife.dateingappbackend.service;

import com.destroyedlife.dateingappbackend.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Base64;

@Service
public class JwtTokenService {

    @Value("${auth.key}")
    private String secretKey;

    @Value("${auth.token.time-to-live}")
    private String ttl;

    @PostConstruct
    private void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(User user)
    {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        LocalDateTime now = LocalDateTime.now();

        Period period = Period.parse(ttl);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Timestamp.valueOf(now))
            .setExpiration(Timestamp.valueOf(now.plus(period)))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }
}
