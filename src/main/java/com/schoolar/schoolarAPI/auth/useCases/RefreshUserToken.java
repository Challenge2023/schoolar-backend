package com.schoolar.schoolarAPI.auth.useCases;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoolar.schoolarAPI.auth.interfaces.IAuthenticationResponse;
import com.schoolar.schoolarAPI.auth.jwt.useCases.ExtractUsername;
import com.schoolar.schoolarAPI.auth.jwt.useCases.GenerateJwtToken;
import com.schoolar.schoolarAPI.auth.jwt.useCases.ValidateToken;
import com.schoolar.schoolarAPI.users.interfaces.IUsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class RefreshUserToken {
    private final IUsersRepository repository;
    private final RevokeUserTokens revoke;
    private final RegisterUserToken register;

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
       final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
       final String refreshToken;
       final String username;
       if (authHeader == null || !authHeader.startsWith("Bearer ")) return;
       refreshToken = authHeader.substring(7);
       username = ExtractUsername.execute(refreshToken);
       if(username != null) {
           var user = repository.findByEmail(username).orElseThrow();
           if (ValidateToken.execute(refreshToken, user)) {
               var accesToken = GenerateJwtToken.execute(user);
               revoke.execute(user);
               register.execute(user.getEnrollment(), accesToken);
               var authResponse = IAuthenticationResponse.builder()
                       .accessToken(accesToken)
                       .refreshToken(refreshToken)
                       .build();
               new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
           }
       }

    }
}
