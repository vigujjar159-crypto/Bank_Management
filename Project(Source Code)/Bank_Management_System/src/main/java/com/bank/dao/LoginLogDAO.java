package com.bank.dao;

import com.bank.model.LoginLog;
import com.bank.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LoginLogDAO {

    // Log Login Attempt
    public void logLogin(LoginLog log) {

        String sql =
                "INSERT INTO login_logs " +
                        "(user_id, login_time, status, ip_address) " +
                        "VALUES (?, CURRENT_TIMESTAMP, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, log.getUserId());
            ps.setString(2, log.getStatus());
            ps.setString(3, log.getIpAddress());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Log Logout
    public void logLogout(int userId) {

        String sql =
                "UPDATE login_logs " +
                        "SET logout_time=CURRENT_TIMESTAMP " +
                        "WHERE user_id=? AND logout_time IS NULL";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
