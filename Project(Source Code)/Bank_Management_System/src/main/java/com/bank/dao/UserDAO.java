package com.bank.dao;

import com.bank.model.User;
import com.bank.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

public class UserDAO {

    // 1. Create User
    public boolean createUser(User u) {

        String sql =
                "INSERT INTO users " +
                        "(username, password_hash, role, " +
                        "linked_customer_id, linked_employee_id) " +
                        "VALUES (?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPasswordHash());
            ps.setString(3, u.getRole());

            if (u.getLinkedCustomerId() == null)
                ps.setNull(4, Types.INTEGER);
            else
                ps.setInt(4, u.getLinkedCustomerId());

            if (u.getLinkedEmployeeId() == null)
                ps.setNull(5, Types.INTEGER);
            else
                ps.setInt(5, u.getLinkedEmployeeId());

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2. Find User by Username
    public User findByUsername(String username) {

        String sql = "SELECT * FROM users WHERE username=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setPasswordHash(rs.getString("password_hash"));
                u.setRole(rs.getString("role"));
                u.setLinkedCustomerId((Integer) rs.getObject("linked_customer_id"));
                u.setLinkedEmployeeId((Integer) rs.getObject("linked_employee_id"));
                u.setLastLogin(rs.getTimestamp("last_login"));
                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 3. Update Last Login
    public void updateLastLogin(int userId) {

        String sql =
                "UPDATE users " +
                        "SET last_login=CURRENT_TIMESTAMP " +
                        "WHERE user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
