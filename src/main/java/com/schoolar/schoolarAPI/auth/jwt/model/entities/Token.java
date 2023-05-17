package com.schoolar.schoolarAPI.auth.jwt.model.entities;

import com.schoolar.schoolarAPI.constants.TokenTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(collection = "tokens")
public class Token {
    @Id
    private ObjectId id;

    private String token;

    private TokenTypes tokenType = TokenTypes.BEARER;

    private boolean revoked = false;

    private boolean expired = false;

    private String user;

}
