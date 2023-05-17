package com.schoolar.schoolarAPI.auth.useCases;

import com.schoolar.schoolarAPI.auth.interfaces.IAuthenticationResponse;
import com.schoolar.schoolarAPI.auth.jwt.useCases.GenerateJwtToken;
import com.schoolar.schoolarAPI.auth.jwt.useCases.GenerateRefreshToken;
import com.schoolar.schoolarAPI.auth.interfaces.IAuthenticationRequest;
import com.schoolar.schoolarAPI.users.interfaces.IUsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticateUser {

    private final AuthenticationManager manager;

    private final IUsersRepository repository;

    private final RevokeUserTokens revoke;

    private final RegisterUserToken register;

    private final GenerateRefreshToken refresh;


    public IAuthenticationResponse execute(IAuthenticationRequest request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getUsername())
                .orElseThrow();
        var jwtToken = GenerateJwtToken.execute(user);
        var refreshToken = refresh.execute(user);
        revoke.execute(user);
        register.execute(user.getEnrollment(), jwtToken);
        return IAuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
}
