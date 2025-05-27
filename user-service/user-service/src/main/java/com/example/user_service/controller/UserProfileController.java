package com.example.user_service.controller;

import com.example.user_service.model.UserProfile;
import com.example.user_service.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private UserProfileService service;

    @PostMapping
    public ResponseEntity<UserProfile> createOrUpdate(@RequestBody UserProfile profile) {
        UserProfile saved = service.createOrUpdateProfile(profile);
        return ResponseEntity.ok(saved);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserProfile> getProfile(@PathVariable Long userId) {
        return service.getProfile(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/update")
    public ResponseEntity<UserProfile> updateProfile(@RequestBody UserProfile profile) {
        return ResponseEntity.ok(service.updateProfile(profile));
    }

}
