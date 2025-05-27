package com.example.auth_service.service;

import com.example.auth_service.model.AuthUser;
import com.example.auth_service.repository.AuthUserRepository;
import com.example.auth_service.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {


    @Autowired
    private AuthUserRepository repo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RestTemplate restTemplate;

    private final String USER_SERVICE_URL = "http://localhost:8081/profile"; // replace with Eureka lb://USER-SERVICE if using gateway

    public String register(AuthUser user) {
        Optional<AuthUser> existing = repo.findByUsername(user.getUsername());
        if (existing.isPresent()) return "User already exists";

        // Save user in auth-service
        user.setPassword(user.getPassword()); // TODO: Hash later
        user.setRole(user.getRole());
        AuthUser savedUser = repo.save(user);

        // Generate JWT token
        String token = jwtUtil.generateToken(user.getUsername(), savedUser.getId(), user.getRole());

        // Make call to user-service to create UserProfile
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> userProfile = new HashMap<>();
        userProfile.put("username", user.getUsername());
        userProfile.put("userId", savedUser.getId());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(userProfile, headers);

        try {
            restTemplate.postForEntity(USER_SERVICE_URL, request, String.class);
        } catch (Exception e) {
            System.out.println("❌ Error while creating user profile: " + e.getMessage());
        }

        return "User registered successfully!";
    }



    public String authenticate(AuthUser user) {
        Optional<AuthUser> existing = repo.findByUsername(user.getUsername());
        if (existing.isPresent() && existing.get().getPassword().equals(user.getPassword())) {
            AuthUser validUser = existing.get(); // ✅ DB se aaya user
            return jwtUtil.generateToken(
                    validUser.getUsername(),
                    validUser.getId(),
                    validUser.getRole()
            );
        }
        return "Invalid credentials";
    }

}