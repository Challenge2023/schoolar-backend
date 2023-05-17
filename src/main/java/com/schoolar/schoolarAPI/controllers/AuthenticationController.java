package com.schoolar.schoolarAPI.controllers;

import com.schoolar.schoolarAPI.auth.interfaces.IAuthenticationRequest;
import com.schoolar.schoolarAPI.auth.interfaces.IAuthenticationResponse;
import com.schoolar.schoolarAPI.auth.useCases.AuthenticateUser;
import com.schoolar.schoolarAPI.auth.useCases.RefreshUserToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticateUser authenticate;
    private final RefreshUserToken refresh;

    @PostMapping("/authenticate")
    public ResponseEntity<IAuthenticationResponse> authenticate(@RequestBody IAuthenticationRequest request) {
        return ResponseEntity.ok(authenticate.execute(request));
    }

    @PostMapping("/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        refresh.execute(request, response);
    }
}
