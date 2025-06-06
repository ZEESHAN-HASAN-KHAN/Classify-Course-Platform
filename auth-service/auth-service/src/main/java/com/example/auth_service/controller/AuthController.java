package com.example.auth_service.controller;

import com.example.auth_service.model.AuthUser;
import com.example.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/signup")
    public String signup(@RequestBody AuthUser user) {
        return service.register(user);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/login")
    public String login(@RequestBody AuthUser user) {
        return service.authenticate(user);
    }
}
