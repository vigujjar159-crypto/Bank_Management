package com.bank.dao;

import com.bank.model.Customer;
import com.bank.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDAO {

    // 1. Customer Registration
    public boolean registerCustomer(Customer c) {

        String sql =
                "INSERT INTO customers " +
                        "(first_name, dob, gender, phone, email, address, kyc_status, created_at, status) " +
                        "VALUES (?,?,?,?,?,?, 'Pending', CURRENT_TIMESTAMP, 'Active')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getFirstName());
            ps.setDate(2, c.getDob());
            ps.setString(3, c.getGender());
            ps.setString(4, c.getPhone());
            ps.setString(5, c.getEmail());
            ps.setString(6, c.getAddress());

            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2. Update Customer Profile
    public boolean updateProfile(int customerId, String address) {

        String sql = "UPDATE customers SET address=? WHERE customer_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, address);
            ps.setInt(2, customerId);
            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. KYC Verification
    public boolean updateKycStatus(int customerId, String kycStatus) {

        String sql = "UPDATE customers SET kyc_status=? WHERE customer_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, kycStatus);
            ps.setInt(2, customerId);
            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4. Activate / Deactivate Customer
    public boolean updateCustomerStatus(int customerId, String status) {

        String sql = "UPDATE customers SET status=? WHERE customer_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, customerId);
            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5. Search Customer by Phone / Email
    public Customer findCustomer(String phoneOrEmail) {

        String sql =
                "SELECT * FROM customers " +
                        "WHERE phone=? OR email=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, phoneOrEmail);
            ps.setString(2, phoneOrEmail);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer c = new Customer();
                c.setCustomerId(rs.getInt("customer_id"));
                c.setFirstName(rs.getString("first_name"));
                c.setDob(rs.getDate("dob"));
                c.setGender(rs.getString("gender"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setAddress(rs.getString("address"));
                c.setKycStatus(rs.getString("kyc_status"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                c.setStatus(rs.getString("status"));
                return c;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
