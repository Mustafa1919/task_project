package com.mstech.taskpomodoro.security.repository;

import com.mstech.taskpomodoro.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
