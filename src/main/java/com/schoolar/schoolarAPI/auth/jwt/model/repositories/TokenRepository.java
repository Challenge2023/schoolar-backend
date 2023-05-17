package com.schoolar.schoolarAPI.auth.jwt.model.repositories;

import com.schoolar.schoolarAPI.auth.jwt.interfaces.ITokensRepository;
import com.schoolar.schoolarAPI.auth.jwt.model.entities.Token;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TokenRepository implements ITokensRepository {

    private final MongoTemplate mongoTemplate;
    @Override
    public void register(Token token) {
        mongoTemplate.save(token, "tokens");
    }

    @Override
    public void registerAll(List<Token> tokens) {
        mongoTemplate.insertAll(tokens);
    }

    @Override
    public List<Token> findValidTokensByUser(String enrollment) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user.enrollment").is(enrollment)
                .andOperator(Criteria.where("expired").is(false),
                        Criteria.where("revoked").is(false)));
        return mongoTemplate.find(query, Token.class, "tokens");
    }

    @Override
    public Optional<Token> findByToken(String token) {
        Query query = new Query();
        query.addCriteria(Criteria.where("token").is(token));
        return Optional.ofNullable(mongoTemplate.findOne(query, Token.class, "tokens"));
    }
}
