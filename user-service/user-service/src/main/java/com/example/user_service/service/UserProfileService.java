package com.example.user_service.service;

import com.example.user_service.model.UserProfile;
import com.example.user_service.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository repo;

    public UserProfile createOrUpdateProfile(UserProfile profile) {
        return repo.save(profile);
    }

    public Optional<UserProfile> getProfile(Long userId) {
        return repo.findById(userId);
    }
    public UserProfile updateProfile(UserProfile profile) {
        Optional<UserProfile> existing = repo.findById(profile.getUserId());
        if (existing.isPresent()) {
            UserProfile existingProfile = existing.get();
            existingProfile.setEmail(profile.getEmail());
            existingProfile.setFullName(profile.getFullName());
            existingProfile.setBio(profile.getBio());
            existingProfile.setProfileImageUrl(profile.getProfileImageUrl());
            return repo.save(existingProfile);
        }
        throw new RuntimeException("Profile not found!");
    }
}
