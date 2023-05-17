package com.schoolar.schoolarAPI.auth.jwt.useCases;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public class ExtractUsername {
    public static String execute(String token) {
        return ExtractClaim.execute(token, Claims::getSubject);
    }
}
