package com.schoolar.schoolarAPI.auth.useCases;

import com.schoolar.schoolarAPI.auth.jwt.interfaces.ITokensRepository;
import com.schoolar.schoolarAPI.users.model.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RevokeUserTokens {
    private final ITokensRepository repository;

    protected void execute(User user) {
        var validUserTokens = repository.findValidTokensByUser(user.getEnrollment());
        if (validUserTokens.isEmpty()) return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        repository.registerAll(validUserTokens);
    }
}
