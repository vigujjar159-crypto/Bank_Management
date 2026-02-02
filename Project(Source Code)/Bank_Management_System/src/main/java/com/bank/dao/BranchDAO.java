package com.bank.dao;

import com.bank.model.Branch;
import com.bank.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BranchDAO {

    // 1. Add New Branch
    public boolean addBranch(Branch b) {
        String sql =
                "INSERT INTO branches " +
                        "(branch_name, ifsc_code, address, phone) " +
                        "VALUES (?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, b.getBranchName());
            ps.setString(2, b.getIfscCode());
            ps.setString(3, b.getAddress());
            ps.setString(4, b.getPhone());

            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2. Get Branch by ID
    public Branch getBranchById(int branchId) {
        String sql = "SELECT * FROM branches WHERE branch_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, branchId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Branch b = new Branch();
                b.setBranchId(rs.getInt("branch_id"));
                b.setBranchName(rs.getString("branch_name"));
                b.setIfscCode(rs.getString("ifsc_code"));
                b.setAddress(rs.getString("address"));
                b.setPhone(rs.getString("phone"));
                return b;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 3. Update Branch Details
    public boolean updateBranch(Branch b) {
        String sql =
                "UPDATE branches SET " +
                        "branch_name=?, address=?, phone=? " +
                        "WHERE branch_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, b.getBranchName());
            ps.setString(2, b.getAddress());
            ps.setString(3, b.getPhone());
            ps.setInt(4, b.getBranchId());

            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4. List All Branches
    public List<Branch> getAllBranches() {
        List<Branch> list = new ArrayList<>();
        String sql = "SELECT * FROM branches";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Branch b = new Branch();
                b.setBranchId(rs.getInt("branch_id"));
                b.setBranchName(rs.getString("branch_name"));
                b.setIfscCode(rs.getString("ifsc_code"));
                b.setAddress(rs.getString("address"));
                b.setPhone(rs.getString("phone"));
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
