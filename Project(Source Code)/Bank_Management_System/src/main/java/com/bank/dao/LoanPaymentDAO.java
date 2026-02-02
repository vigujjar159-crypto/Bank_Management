package com.bank.dao;

import com.bank.model.LoanPayment;
import com.bank.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LoanPaymentDAO {

    // 1. Pay EMI
    public boolean payEmi(LoanPayment p) {

        String sql =
                "INSERT INTO loan_payments " +
                        "(loan_id, amount_paid, payment_date, remaining_amount) " +
                        "VALUES (?,?, CURRENT_DATE, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getLoanId());
            ps.setDouble(2, p.getAmountPaid());
            ps.setDouble(3, p.getRemainingAmount());

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
