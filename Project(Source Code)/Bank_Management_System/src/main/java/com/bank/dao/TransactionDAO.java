package com.bank.dao;

import com.bank.model.Transaction;
import com.bank.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    // ===== DEPOSIT =====
    public boolean deposit(int accountId, double amount) {
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);

            double newBalance = updateBalance(con, accountId, amount, true);

            insertTransaction(con,
                    new Transaction(accountId, "Deposit",
                            amount, null, newBalance));

            con.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ===== WITHDRAW =====
    public boolean withdraw(int accountId, double amount) {
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);

            double newBalance = updateBalance(con, accountId, amount, false);

            if (newBalance < 0) {
                con.rollback();
                return false;
            }

            insertTransaction(con,
                    new Transaction(accountId, "Withdraw",
                            amount, null, newBalance));

            con.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ===== FUND TRANSFER =====
    public boolean transfer(int fromAccount, int toAccount, double amount) {
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);

            double senderBalance = updateBalance(con, fromAccount, amount, false);
            if (senderBalance < 0) {
                con.rollback();
                return false;
            }

            double receiverBalance = updateBalance(con, toAccount, amount, true);

            insertTransaction(con,
                    new Transaction(fromAccount, "Transfer",
                            amount, toAccount, senderBalance));

            insertTransaction(con,
                    new Transaction(toAccount, "Transfer",
                            amount, fromAccount, receiverBalance));

            con.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ===== MINI STATEMENT =====
    public List<Transaction> miniStatement(int accountId) {

        List<Transaction> list = new ArrayList<>();

        String sql =
                "SELECT * FROM transactions " +
                        "WHERE account_id=? " +
                        "ORDER BY txn_date DESC " +
                        "LIMIT 5";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapTransaction(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ===== FULL STATEMENT =====
    public List<Transaction> fullStatement(int accountId) {

        List<Transaction> list = new ArrayList<>();

        String sql =
                "SELECT * FROM transactions " +
                        "WHERE account_id=? " +
                        "ORDER BY txn_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapTransaction(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ===== HELPER METHODS =====
    private double updateBalance(Connection con, int accId,
                                 double amount, boolean isCredit) throws Exception {

        String select = "SELECT balance FROM accounts WHERE account_id=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1, accId);
        ResultSet rs = ps.executeQuery();

        if (!rs.next())
            throw new Exception("Account not found");

        double balance = rs.getDouble(1);
        double newBalance = isCredit ? balance + amount : balance - amount;

        String update = "UPDATE accounts SET balance=? WHERE account_id=?";
        PreparedStatement ups = con.prepareStatement(update);
        ups.setDouble(1, newBalance);
        ups.setInt(2, accId);
        ups.executeUpdate();

        return newBalance;
    }

    private void insertTransaction(Connection con, Transaction t) throws Exception {

        String sql =
                "INSERT INTO transactions " +
                        "(account_id, txn_type, amount, txn_date, " +
                        "reference_account, balance_after_txn) " +
                        "VALUES (?,?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, t.getAccountId());
        ps.setString(2, t.getTxnType());
        ps.setDouble(3, t.getAmount());
        ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

        if (t.getReferenceAccount() == null)
            ps.setNull(5, Types.INTEGER);
        else
            ps.setInt(5, t.getReferenceAccount());

        ps.setDouble(6, t.getBalanceAfterTxn());
        ps.executeUpdate();
    }

    private Transaction mapTransaction(ResultSet rs) throws Exception {

        Transaction t = new Transaction();
        t.setTransactionId(rs.getInt("transaction_id"));
        t.setAccountId(rs.getInt("account_id"));
        t.setTxnType(rs.getString("txn_type"));
        t.setAmount(rs.getDouble("amount"));
        t.setTxnDate(rs.getTimestamp("txn_date"));

        Object ref = rs.getObject("reference_account");
        if (ref != null)
            t.setReferenceAccount((Integer) ref);

        t.setBalanceAfterTxn(rs.getDouble("balance_after_txn"));
        return t;
    }
}
