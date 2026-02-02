package com.bank.model;

import java.sql.Date;

public class Account {

    private int accountId;
    private int customerId;   // FK → customers.customer_id
    private int branchId;     // FK → branches.branch_id
    private String accountType; // Savings / Current / FD
    private double balance;
    private Date openedOn;
    private String status; // Active / Closed

    // ===== Constructors =====
    public Account() {}

    public Account(int customerId, int branchId,
                   String accountType, double balance) {
        this.customerId = customerId;
        this.branchId = branchId;
        this.accountType = accountType;
        this.balance = balance;
    }

    // ===== Getters & Setters =====
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getOpenedOn() {
        return openedOn;
    }

    public void setOpenedOn(Date openedOn) {
        this.openedOn = openedOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
