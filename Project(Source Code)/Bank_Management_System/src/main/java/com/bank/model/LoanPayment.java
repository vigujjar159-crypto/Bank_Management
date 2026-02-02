package com.bank.model;

import java.sql.Date;

public class LoanPayment {

    private int paymentId;
    private int loanId;             // FK → loans.loan_id
    private double amountPaid;
    private Date paymentDate;
    private double remainingAmount;

    // ===== Constructors =====
    public LoanPayment() {}

    public LoanPayment(int loanId, double amountPaid,
                       double remainingAmount) {
        this.loanId = loanId;
        this.amountPaid = amountPaid;
        this.remainingAmount = remainingAmount;
    }

    // ===== Getters & Setters =====
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }
}
