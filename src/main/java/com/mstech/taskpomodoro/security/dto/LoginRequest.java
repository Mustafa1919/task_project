package com.mstech.taskpomodoro.security.dto;

public record LoginRequest(
        String userName,
        String password
) {
}
