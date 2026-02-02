package com.bank.model;

import java.sql.Date;

public class Employee {

    private int employeeId;
    private int branchId;        // FK → branches.branch_id
    private String firstName;
    private String email;
    private String phone;
    private String role;         // Admin / Manager / Staff
    private double salary;
    private Date dateJoined;
    private String status;       // Active / Inactive

    // ===== Constructors =====
    public Employee() {}

    public Employee(int branchId, String firstName, String email,
                    String phone, String role, double salary) {
        this.branchId = branchId;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.salary = salary;
    }

    // ===== Getters & Setters =====
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
