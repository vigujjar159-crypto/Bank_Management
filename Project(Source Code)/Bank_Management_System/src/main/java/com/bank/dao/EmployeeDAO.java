package com.bank.dao;

import com.bank.model.Employee;
import com.bank.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // 1. Add Employee
    public boolean addEmployee(Employee e) {

        String sql =
                "INSERT INTO employees " +
                        "(branch_id, first_name, email, phone, role, salary, date_joined, status) " +
                        "VALUES (?,?,?,?,?,?, CURRENT_DATE, 'Active')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, e.getBranchId());
            ps.setString(2, e.getFirstName());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getPhone());
            ps.setString(5, e.getRole());
            ps.setDouble(6, e.getSalary());

            return ps.executeUpdate() == 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // 2. Update Employee Details
    public boolean updateEmployee(Employee e) {

        String sql =
                "UPDATE employees " +
                        "SET first_name=?, phone=?, role=?, salary=? " +
                        "WHERE employee_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getFirstName());
            ps.setString(2, e.getPhone());
            ps.setString(3, e.getRole());
            ps.setDouble(4, e.getSalary());
            ps.setInt(5, e.getEmployeeId());

            return ps.executeUpdate() == 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // 3. Activate / Deactivate Employee
    public boolean updateStatus(int employeeId, String status) {

        String sql = "UPDATE employees SET status=? WHERE employee_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, employeeId);
            return ps.executeUpdate() == 1;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // 4. Get Employee by ID
    public Employee getEmployeeById(int empId) {

        String sql = "SELECT * FROM employees WHERE employee_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Employee e = new Employee();
                e.setEmployeeId(rs.getInt("employee_id"));
                e.setBranchId(rs.getInt("branch_id"));
                e.setFirstName(rs.getString("first_name"));
                e.setEmail(rs.getString("email"));
                e.setPhone(rs.getString("phone"));
                e.setRole(rs.getString("role"));
                e.setSalary(rs.getDouble("salary"));
                e.setDateJoined(rs.getDate("date_joined"));
                e.setStatus(rs.getString("status"));
                return e;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // 5. Staff List per Branch
    public List<Employee> getEmployeesByBranch(int branchId) {

        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE branch_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, branchId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Employee e = new Employee();
                e.setEmployeeId(rs.getInt("employee_id"));
                e.setFirstName(rs.getString("first_name"));
                e.setRole(rs.getString("role"));
                e.setStatus(rs.getString("status"));
                list.add(e);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
