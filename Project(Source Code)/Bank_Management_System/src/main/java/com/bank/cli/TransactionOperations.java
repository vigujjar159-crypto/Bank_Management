package com.bank.cli;

import com.bank.dao.TransactionDAO;
import com.bank.model.Transaction;

import java.util.List;
import java.util.Scanner;

public class TransactionOperations {
    private static final TransactionDAO txnDAO = new TransactionDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void runTransactionModule() {
        while (true) {
            System.out.println("\n===== Transaction Module =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Fund Transfer");
            System.out.println("4. Mini Statement");
            System.out.println("5. Full Statement");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    transfer();
                    break;
                case 4:
                    miniStatement();
                    break;
                case 5:
                    fullStatement();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
                    break;
            }
        }
    }

    private static void deposit() {
        System.out.print("Account ID: ");
        int accountId = sc.nextInt();
        System.out.print("Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        if (txnDAO.deposit(accountId, amount)) {
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Error in deposit.");
        }
    }

    private static void withdraw() {
        System.out.print("Account ID: ");
        int accountId = sc.nextInt();
        System.out.print("Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        if (txnDAO.withdraw(accountId, amount)) {
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Insufficient balance or error.");
        }
    }

    private static void transfer() {
        System.out.print("Sender Account ID: ");
        int fromAccount = sc.nextInt();
        System.out.print("Receiver Account ID: ");
        int toAccount = sc.nextInt();
        System.out.print("Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        if (txnDAO.transfer(fromAccount, toAccount, amount)) {
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Transfer failed (Insufficient balance or error).");
        }
    }

    private static void miniStatement() {
        System.out.print("Account ID: ");
        int accountId = sc.nextInt();
        sc.nextLine();

        List<Transaction> list = txnDAO.miniStatement(accountId);
        if (list.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("\n--- Mini Statement ---");
        for (Transaction t : list) {
            System.out.println("Txn ID: " + t.getTransactionId() +
                    ", Type: " + t.getTxnType() +
                    ", Amount: " + t.getAmount() +
                    ", Date: " + t.getTxnDate() +
                    ", Ref Acc: " + t.getReferenceAccount() +
                    ", Balance After: " + t.getBalanceAfterTxn());
        }
    }

    private static void fullStatement() {
        System.out.print("Account ID: ");
        int accountId = sc.nextInt();
        sc.nextLine();

        List<Transaction> list = txnDAO.fullStatement(accountId);
        if (list.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("\n--- Full Statement ---");
        for (Transaction t : list) {
            System.out.println("Txn ID: " + t.getTransactionId() +
                    ", Type: " + t.getTxnType() +
                    ", Amount: " + t.getAmount() +
                    ", Date: " + t.getTxnDate() +
                    ", Ref Acc: " + t.getReferenceAccount() +
                    ", Balance After: " + t.getBalanceAfterTxn());
        }
    }
}
