package com.bank.model;

import java.sql.Timestamp;

public class LoginLog {

    private int logId;
    private int userId;          // FK → users.user_id
    private Timestamp loginTime;
    private Timestamp logoutTime;
    private String status;       // Success / Failed
    private String ipAddress;

    // ===== Constructors =====
    public LoginLog() {}

    public LoginLog(int userId, String status, String ipAddress) {
        this.userId = userId;
        this.status = status;
        this.ipAddress = ipAddress;
    }

    // ===== Getters & Setters =====
    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public Timestamp getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
