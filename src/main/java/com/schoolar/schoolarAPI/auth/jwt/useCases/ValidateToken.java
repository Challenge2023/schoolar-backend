package com.schoolar.schoolarAPI.auth.jwt.useCases;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ValidateToken {
    public static boolean execute(String token, UserDetails userDetails) {
        final String userName = ExtractUsername.execute(token);
        boolean isTokenExpired = VerifyTokenExpiration.execute(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired;
    }
}
