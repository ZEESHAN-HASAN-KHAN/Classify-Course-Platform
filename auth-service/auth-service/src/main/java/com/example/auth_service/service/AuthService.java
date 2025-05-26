package com.example.auth_service.service;

import com.example.auth_service.model.AuthUser;
import com.example.auth_service.repository.AuthUserRepository;
import com.example.auth_service.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {


    @Autowired
    private AuthUserRepository repo;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(AuthUser user) {
        Optional<AuthUser> existing = repo.findByUsername(user.getUsername());
        if (existing.isPresent()) return "User already exists";

        user.setPassword(user.getPassword()); // 🔒 TODO: Hash later
        user.setRole("USER");
        repo.save(user);
        return "User registered successfully!";
    }

    public String authenticate(AuthUser user) {
        Optional<AuthUser> existing = repo.findByUsername(user.getUsername());
        if (existing.isPresent() && existing.get().getPassword().equals(user.getPassword())) {
            return jwtUtil.generateToken(user.getUsername());
        }
        return "Invalid credentials";
}
}