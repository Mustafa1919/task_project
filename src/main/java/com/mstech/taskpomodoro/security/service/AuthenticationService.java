package com.mstech.taskpomodoro.security.service;

import com.mstech.taskpomodoro.security.dto.LoginRequest;
import com.mstech.taskpomodoro.security.dto.LoginResponse;
import com.mstech.taskpomodoro.security.dto.SignInRequest;
import com.mstech.taskpomodoro.security.dto.SignInResponse;
import com.mstech.taskpomodoro.security.entity.ROLE_ENUM;
import com.mstech.taskpomodoro.security.entity.Role;
import com.mstech.taskpomodoro.security.entity.User;
import com.mstech.taskpomodoro.security.jwt.JwtService;
import com.mstech.taskpomodoro.security.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(JwtService jwtService, AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public SignInResponse signIn(SignInRequest signInRequest){
        List<Role> roles = signInRequest.roles().stream().map(Role::new).toList();
        User user = new User(roles
                ,signInRequest.userName()
                , passwordEncoder.encode(signInRequest.password()));
        userRepository.save(user);
        String token = jwtService.generateToken("username");
        return new SignInResponse(user.getUserName(), token, signInRequest.roles());
    }

    public LoginResponse logIn(LoginRequest loginRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.userName(),loginRequest.password()));
        UserDetails userDetails = ((UserDetails)authentication.getPrincipal());
        String token = jwtService.generateToken(authentication);
        return new LoginResponse(userDetails.getUsername()
                , token
                , userDetails.getAuthorities().stream().map(grande-> ROLE_ENUM.getRoleByName(grande.getAuthority())).toList());
    }
}
