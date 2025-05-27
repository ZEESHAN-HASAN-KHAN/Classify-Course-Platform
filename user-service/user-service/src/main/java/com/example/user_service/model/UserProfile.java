package com.example.user_service.model;

import javax.persistence.*;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    private Long userId; // Same ID as in auth-service

    private String fullName;
    private String email;
    private String bio;
    private String profileImageUrl;

    public UserProfile() {}

    public UserProfile(Long userId, String fullName, String email, String bio, String profileImageUrl) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.bio = bio;
        this.profileImageUrl = profileImageUrl;
    }

    // Getters and Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
