package com.bank.dao;

import com.bank.model.Account;
import com.bank.util.DBConnection;

import java.sql.*;

public class AccountDAO {

    // 1. Open Account
    public boolean openAccount(Account acc) {

        String sql =
                "INSERT INTO accounts " +
                        "(customer_id, branch_id, account_type, balance, opened_on, status) " +
                        "VALUES (?, ?, ?, ?, CURRENT_DATE, 'Active')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, acc.getCustomerId());
            ps.setInt(2, acc.getBranchId());
            ps.setString(3, acc.getAccountType());
            ps.setDouble(4, acc.getBalance());

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2. Close Account
    public boolean closeAccount(int accountId) {

        String sql = "UPDATE accounts SET status='Closed' WHERE account_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, accountId);
            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. View Account Details
    public Account getAccountById(int accountId) {

        String sql = "SELECT * FROM accounts WHERE account_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Account acc = new Account();
                acc.setAccountId(rs.getInt("account_id"));
                acc.setCustomerId(rs.getInt("customer_id"));
                acc.setBranchId(rs.getInt("branch_id"));
                acc.setAccountType(rs.getString("account_type"));
                acc.setBalance(rs.getDouble("balance"));
                acc.setOpenedOn(rs.getDate("opened_on"));
                acc.setStatus(rs.getString("status"));
                return acc;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 4. Change Account Type
    public boolean changeAccountType(int accountId, String newType) {

        String sql = "UPDATE accounts SET account_type=? WHERE account_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newType);
            ps.setInt(2, accountId);
            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5. Balance Check
    public double getBalance(int accountId) {

        String sql = "SELECT balance FROM accounts WHERE account_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("balance");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
