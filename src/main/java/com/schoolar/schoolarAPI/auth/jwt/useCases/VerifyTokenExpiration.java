package com.schoolar.schoolarAPI.auth.jwt.useCases;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VerifyTokenExpiration {
    protected static boolean execute(String token) {
        return extractExpiration(token).before(new Date());
    }

    private static Date extractExpiration(String token) {
        return ExtractClaim.execute(token, Claims::getExpiration);
    }
}
