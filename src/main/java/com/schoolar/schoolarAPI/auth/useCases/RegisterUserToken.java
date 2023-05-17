package com.schoolar.schoolarAPI.auth.useCases;

import com.schoolar.schoolarAPI.auth.jwt.interfaces.ITokensRepository;
import com.schoolar.schoolarAPI.auth.jwt.model.entities.Token;
import com.schoolar.schoolarAPI.constants.TokenTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserToken {
    private final ITokensRepository repository;
    protected void execute(String enrollment, String jwt) {
        var token = Token.builder()
                .user(enrollment)
                .token(jwt)
                .tokenType(TokenTypes.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        repository.register(token);
    }
}
