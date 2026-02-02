package com.bank.model;

import java.sql.Date;

public class Loan {

    private int loanId;
    private int customerId;      // FK → customers.customer_id
    private String loanType;     // Home / Personal / Vehicle
    private double amount;
    private double interestRate;
    private int tenureMonths;
    private double emiAmount;
    private String loanStatus;   // Pending / Approved / Rejected / Active / Completed
    private Date appliedOn;
    private Date approvedOn;     // nullable

    // ===== Constructors =====
    public Loan() {}

    public Loan(int customerId, String loanType,
                double amount, double interestRate, int tenureMonths) {
        this.customerId = customerId;
        this.loanType = loanType;
        this.amount = amount;
        this.interestRate = interestRate;
        this.tenureMonths = tenureMonths;
    }

    // ===== Getters & Setters =====
    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getTenureMonths() {
        return tenureMonths;
    }

    public void setTenureMonths(int tenureMonths) {
        this.tenureMonths = tenureMonths;
    }

    public double getEmiAmount() {
        return emiAmount;
    }

    public void setEmiAmount(double emiAmount) {
        this.emiAmount = emiAmount;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Date getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(Date appliedOn) {
        this.appliedOn = appliedOn;
    }

    public Date getApprovedOn() {
        return approvedOn;
    }

    public void setApprovedOn(Date approvedOn) {
        this.approvedOn = approvedOn;
    }
}
