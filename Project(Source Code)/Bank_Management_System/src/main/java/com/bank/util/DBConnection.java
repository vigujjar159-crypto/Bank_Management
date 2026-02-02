package com.bank.util;

import java.sql.*;

public class DBConnection {

    private static final String url = "jdbc:mysql://localhost:3306/bankmanagement";
    private static final String username = "root";
    private static final String password = "Tush@r2005";

    // establish DB connection
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return con;
    }

    // create tables if not exists
    public static void createTables() {
        try (Connection con = getConnection()) {
            if (con != null) {
                Statement stmt = con.createStatement();

                String createBranches =
                        "CREATE TABLE IF NOT EXISTS branches (" +
                                "branch_id INT AUTO_INCREMENT PRIMARY KEY," +
                                "branch_name VARCHAR(100) NOT NULL," +
                                "ifsc_code VARCHAR(20) UNIQUE," +
                                "address TEXT NOT NULL," +
                                "phone VARCHAR(15) NOT NULL)";

                String createCustomers =
                        "CREATE TABLE IF NOT EXISTS customers (" +
                                "customer_id INT AUTO_INCREMENT PRIMARY KEY," +
                                "first_name VARCHAR(50) NOT NULL," +
                                "dob DATE NOT NULL," +
                                "gender VARCHAR(10) NOT NULL," +
                                "phone VARCHAR(15) UNIQUE," +
                                "email VARCHAR(100) UNIQUE," +
                                "address TEXT NOT NULL," +
                                "kyc_status ENUM('Pending','Verified','Rejected') NOT NULL," +
                                "created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                                "status ENUM('Active','Inactive') NOT NULL)";

                String createAccounts =
                        "CREATE TABLE IF NOT EXISTS accounts (" +
                                "account_id INT AUTO_INCREMENT PRIMARY KEY," +
                                "customer_id INT," +
                                "branch_id INT," +
                                "account_type ENUM('Savings','Current','FD') NOT NULL," +
                                "balance DECIMAL(15,2) NOT NULL," +
                                "opened_on DATE NOT NULL," +
                                "status ENUM('Active','Closed') NOT NULL," +
                                "FOREIGN KEY (customer_id) REFERENCES customers(customer_id)," +
                                "FOREIGN KEY (branch_id) REFERENCES branches(branch_id))";

                String createTransactions =
                        "CREATE TABLE IF NOT EXISTS transactions (" +
                                "transaction_id INT AUTO_INCREMENT PRIMARY KEY," +
                                "account_id INT," +
                                "txn_type ENUM('Deposit','Withdraw','Transfer') NOT NULL," +
                                "amount DECIMAL(15,2) NOT NULL," +
                                "txn_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                                "reference_account INT," +
                                "balance_after_txn DECIMAL(15,2) NOT NULL," +
                                "FOREIGN KEY (account_id) REFERENCES accounts(account_id))";

                String createEmployees =
                        "CREATE TABLE IF NOT EXISTS employees (" +
                                "employee_id INT AUTO_INCREMENT PRIMARY KEY," +
                                "branch_id INT NOT NULL," +
                                "first_name VARCHAR(50) NOT NULL," +
                                "email VARCHAR(50) UNIQUE," +
                                "phone VARCHAR(100) NOT NULL," +
                                "role ENUM('Admin','Manager','Staff') NOT NULL," +
                                "salary DECIMAL(10,2) NOT NULL," +
                                "date_joined DATE NOT NULL," +
                                "status ENUM('Active','Inactive') NOT NULL," +
                                "FOREIGN KEY (branch_id) REFERENCES branches(branch_id))";

                String createLoans =
                        "CREATE TABLE IF NOT EXISTS loans (" +
                                "loan_id INT AUTO_INCREMENT PRIMARY KEY," +
                                "customer_id INT," +
                                "loan_type ENUM('Home','Personal','Vehicle') NOT NULL," +
                                "amount DECIMAL(15,2) NOT NULL," +
                                "interest_rate DECIMAL(15,2) NOT NULL," +
                                "tenure_months INT NOT NULL," +
                                "emi_amount DECIMAL(10,2) NOT NULL," +
                                "loan_status ENUM('Pending','Approved','Rejected','Active','Completed') NOT NULL," +
                                "applied_on DATE NOT NULL," +
                                "approved_on DATE," +
                                "FOREIGN KEY (customer_id) REFERENCES customers(customer_id))";

                String createLoanPayments =
                        "CREATE TABLE IF NOT EXISTS loan_payments (" +
                                "payment_id INT AUTO_INCREMENT PRIMARY KEY," +
                                "loan_id INT," +
                                "amount_paid DECIMAL(10,2) NOT NULL," +
                                "payment_date DATE NOT NULL," +
                                "remaining_amount DECIMAL(15,2) NOT NULL," +
                                "FOREIGN KEY (loan_id) REFERENCES loans(loan_id))";

                String createUsers =
                        "CREATE TABLE IF NOT EXISTS users (" +
                                "user_id INT AUTO_INCREMENT PRIMARY KEY," +
                                "username VARCHAR(50) UNIQUE," +
                                "password_hash VARCHAR(255) NOT NULL," +
                                "role ENUM('Customer','Employee','Admin') NOT NULL," +
                                "linked_customer_id INT," +
                                "linked_employee_id INT," +
                                "last_login TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                                "FOREIGN KEY (linked_customer_id) REFERENCES customers(customer_id)," +
                                "FOREIGN KEY (linked_employee_id) REFERENCES employees(employee_id))";

                String createLoginLogs =
                        "CREATE TABLE IF NOT EXISTS login_logs (" +
                                "log_id INT AUTO_INCREMENT PRIMARY KEY," +
                                "user_id INT," +
                                "login_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                                "logout_time TIMESTAMP," +
                                "status ENUM('Success','Failed') NOT NULL," +
                                "ip_address VARCHAR(50) NOT NULL," +
                                "FOREIGN KEY (user_id) REFERENCES users(user_id))";

                stmt.addBatch(createBranches);
                stmt.addBatch(createCustomers);
                stmt.addBatch(createAccounts);
                stmt.addBatch(createTransactions);
                stmt.addBatch(createEmployees);
                stmt.addBatch(createLoans);
                stmt.addBatch(createLoanPayments);
                stmt.addBatch(createUsers);
                stmt.addBatch(createLoginLogs);

                stmt.executeBatch();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
