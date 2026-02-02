package com.bank.model;

public class Branch {

    private int branchId;
    private String branchName;
    private String ifscCode;
    private String address;
    private String phone;

    // ===== Constructors =====
    public Branch() {}

    public Branch(String branchName, String ifscCode,
                  String address, String phone) {
        this.branchName = branchName;
        this.ifscCode = ifscCode;
        this.address = address;
        this.phone = phone;
    }

    // ===== Getters & Setters =====
    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
