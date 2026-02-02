package com.bank.dao;

import com.bank.model.Loan;
import com.bank.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LoanDAO {

    // 1. Apply Loan
    public boolean applyLoan(Loan loan) {

        String sql =
                "INSERT INTO loans " +
                        "(customer_id, loan_type, amount, interest_rate, " +
                        "tenure_months, emi_amount, loan_status, applied_on) " +
                        "VALUES (?,?,?,?,?,?, 'Pending', CURRENT_DATE)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, loan.getCustomerId());
            ps.setString(2, loan.getLoanType());
            ps.setDouble(3, loan.getAmount());
            ps.setDouble(4, loan.getInterestRate());
            ps.setInt(5, loan.getTenureMonths());
            ps.setDouble(6, loan.getEmiAmount());

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2. Approve / Reject Loan
    public boolean updateLoanStatus(int loanId, String status) {

        String sql =
                "UPDATE loans " +
                        "SET loan_status=?, approved_on=CURRENT_DATE " +
                        "WHERE loan_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, loanId);

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. EMI Calculation (logic only, DB independent)
    public double calculateEmi(double amount, double rate, int months) {

        double monthlyRate = rate / (12 * 100);

        return (amount * monthlyRate *
                Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);
    }
}
