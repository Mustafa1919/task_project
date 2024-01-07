package com.mstech.taskpomodoro.controller;

import com.mstech.taskpomodoro.security.dto.LoginRequest;
import com.mstech.taskpomodoro.security.dto.LoginResponse;
import com.mstech.taskpomodoro.security.dto.SignInRequest;
import com.mstech.taskpomodoro.security.dto.SignInResponse;
import com.mstech.taskpomodoro.security.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthenticationApi {

    private final AuthenticationService authenticationService;

    public AuthenticationApi(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authenticationService.logIn(loginRequest));
    }

    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<LoginResponse> refreshToken(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<LoginResponse> deleteUser(@RequestParam String userName){
        return ResponseEntity.ok().build();
    }

}
