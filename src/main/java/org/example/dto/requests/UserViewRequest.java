package org.example.dto.requests;

import java.time.LocalDateTime;

public class UserViewRequest {

    private String userId;
    private String username;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private Integer role;

    public UserViewRequest() {}

    public UserViewRequest(String userId, String name, String email, String phoneNumber, LocalDateTime createAt, Integer role) {
        this.userId = userId;
        this.username = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createdAt = createAt;
        this.role = role;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
