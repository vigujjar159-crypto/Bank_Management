package com.bank.model;

import java.sql.Timestamp;

public class User {

    private int userId;
    private String username;
    private String passwordHash;
    private String role;              // Customer / Employee / Admin
    private Integer linkedCustomerId; // FK → customers.customer_id (nullable)
    private Integer linkedEmployeeId; // FK → employees.employee_id (nullable)
    private Timestamp lastLogin;

    // ===== Constructors =====
    public User() {}

    public User(String username, String passwordHash, String role,
                Integer linkedCustomerId, Integer linkedEmployeeId) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.linkedCustomerId = linkedCustomerId;
        this.linkedEmployeeId = linkedEmployeeId;
    }

    // ===== Getters & Setters =====
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getLinkedCustomerId() {
        return linkedCustomerId;
    }

    public void setLinkedCustomerId(Integer linkedCustomerId) {
        this.linkedCustomerId = linkedCustomerId;
    }

    public Integer getLinkedEmployeeId() {
        return linkedEmployeeId;
    }

    public void setLinkedEmployeeId(Integer linkedEmployeeId) {
        this.linkedEmployeeId = linkedEmployeeId;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }
}
