package com.bank.model;

import java.sql.Timestamp;

public class Transaction {

    private int transactionId;
    private int accountId;              // FK → accounts.account_id
    private String txnType;             // Deposit / Withdraw / Transfer
    private double amount;
    private Timestamp txnDate;
    private Integer referenceAccount;   // FK → accounts.account_id (nullable)
    private double balanceAfterTxn;

    // ===== Constructors =====
    public Transaction() {}

    public Transaction(int accountId, String txnType,
                       double amount, Integer referenceAccount,
                       double balanceAfterTxn) {
        this.accountId = accountId;
        this.txnType = txnType;
        this.amount = amount;
        this.referenceAccount = referenceAccount;
        this.balanceAfterTxn = balanceAfterTxn;
    }

    // ===== Getters & Setters =====
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(Timestamp txnDate) {
        this.txnDate = txnDate;
    }

    public Integer getReferenceAccount() {
        return referenceAccount;
    }

    public void setReferenceAccount(Integer referenceAccount) {
        this.referenceAccount = referenceAccount;
    }

    public double getBalanceAfterTxn() {
        return balanceAfterTxn;
    }

    public void setBalanceAfterTxn(double balanceAfterTxn) {
        this.balanceAfterTxn = balanceAfterTxn;
    }
}
