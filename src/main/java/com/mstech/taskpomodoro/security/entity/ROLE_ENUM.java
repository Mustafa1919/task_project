package com.mstech.taskpomodoro.security.entity;

public enum ROLE_ENUM {

    ADMIN,
    USER;

    public static ROLE_ENUM getRoleByName(String name){
        for (ROLE_ENUM role:values()){
            if (role.name().equals(name))
                return role;
        }
        return USER;
    }
}
