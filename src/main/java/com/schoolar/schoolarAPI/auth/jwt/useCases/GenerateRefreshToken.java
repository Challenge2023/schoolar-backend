package com.schoolar.schoolarAPI.auth.jwt.useCases;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class GenerateRefreshToken {
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    public String execute(UserDetails userDetails) {
        return GenerateJwtToken.buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }
}
