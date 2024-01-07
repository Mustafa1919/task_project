package com.mstech.taskpomodoro.security.dto;

import com.mstech.taskpomodoro.security.entity.ROLE_ENUM;

import java.util.List;

public record SignInResponse(
        String userName,
        String token,
        List<ROLE_ENUM> roles
) {
}
