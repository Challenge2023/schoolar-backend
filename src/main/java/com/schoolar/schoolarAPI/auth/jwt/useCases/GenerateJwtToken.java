package com.schoolar.schoolarAPI.auth.jwt.useCases;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class GenerateJwtToken {

    @Value("${application.security.jwt.expiration}")
    private static long jwtExpiration;
    public static String execute(UserDetails userDetails) {
        return execute(new HashMap<>(), userDetails);
    }
    public static String execute(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }
    protected static String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(GetSignKey.execute(), SignatureAlgorithm.HS256)
                .compact();
    }
}
