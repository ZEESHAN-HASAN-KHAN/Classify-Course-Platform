package com.example.auth_service.repository;

import com.example.auth_service.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository  extends JpaRepository<AuthUser,Long> {
    Optional<AuthUser> findByUsername(String username);
}
