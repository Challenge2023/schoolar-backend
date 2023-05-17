package com.schoolar.schoolarAPI.auth.jwt.dtos;

import com.schoolar.schoolarAPI.constants.TokenTypes;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
public class RegisterTokenDTO {
    private String token;

    private  TokenTypes tokenType = TokenTypes.BEARER;

    private boolean revoked;

    private boolean expired;

    @UUID()
    private String user;
}
