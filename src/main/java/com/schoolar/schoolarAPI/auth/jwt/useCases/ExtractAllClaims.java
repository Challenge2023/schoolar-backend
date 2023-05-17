package com.schoolar.schoolarAPI.auth.jwt.useCases;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class ExtractAllClaims {
    protected static Claims execute(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(GetSignKey.execute())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
