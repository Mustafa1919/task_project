package com.mstech.taskpomodoro.security.dto;

import com.mstech.taskpomodoro.security.entity.ROLE_ENUM;

import java.util.List;

public record SignInRequest(
        String userName,
        String password,
        List<ROLE_ENUM> roles
) {
}
