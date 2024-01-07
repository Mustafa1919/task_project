package com.mstech.taskpomodoro.security.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long roleId;
    @Enumerated(EnumType.STRING)
    private ROLE_ENUM role;

    public Role(Long roleId, ROLE_ENUM role) {
        this.roleId = roleId;
        this.role = role;
    }

    public Role() {
    }

    public Role(ROLE_ENUM role) {
        this.role = role;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public ROLE_ENUM getRole() {
        return role;
    }

    public void setRole(ROLE_ENUM role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(roleId, role1.roleId) && role == role1.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, role);
    }
}
