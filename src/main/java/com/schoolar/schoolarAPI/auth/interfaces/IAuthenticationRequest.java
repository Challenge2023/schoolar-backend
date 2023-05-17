package com.schoolar.schoolarAPI.auth.interfaces;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IAuthenticationRequest {
    private String username;
    private String password;
}
