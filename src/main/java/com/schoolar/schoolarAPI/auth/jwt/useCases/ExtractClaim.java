package com.schoolar.schoolarAPI.auth.jwt.useCases;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ExtractClaim {
    protected static <T> T execute (String token, Function<Claims, T> claimsResolver) {
        final Claims claims = ExtractAllClaims.execute(token);
        return claimsResolver.apply(claims);
    }
}
