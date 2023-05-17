package com.schoolar.schoolarAPI.auth.jwt.interfaces;

import com.schoolar.schoolarAPI.auth.jwt.model.entities.Token;

import java.util.List;
import java.util.Optional;

public interface ITokensRepository {

    void register (Token token);

    void registerAll(List<Token> tokens);
    List<Token> findValidTokensByUser(String enrollment);
    Optional<Token> findByToken(String token);
}
