package com.schoolar.schoolarAPI.auth.jwt.useCases;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class GetSignKey {

    private static final String secret_key = "214125432A462D4A614E645267556B58703273357638792F423F4528472B4B62";
    protected static Key execute() {
        byte[] keyBytes = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
