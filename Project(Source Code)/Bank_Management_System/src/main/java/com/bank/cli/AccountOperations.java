package com.bank.cli;

import com.bank.dao.AccountDAO;
import com.bank.model.Account;

import java.util.Scanner;

public class AccountOperations {
    private static final AccountDAO accountDAO = new AccountDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void runAccountModule() {
        while (true) {
            System.out.println("\n===== Account Module =====");
            System.out.println("1. Open Account");
            System.out.println("2. Close Account");
            System.out.println("3. View Account Details");
            System.out.println("4. Change Account Type");
            System.out.println("5. Check Balance");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    openAccount();
                    break;
                case 2:
                    closeAccount();
                    break;
                case 3:
                    viewAccountDetails();
                    break;
                case 4:
                    changeAccountType();
                    break;
                case 5:
                    checkBalance();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
                    break;
            }
        }
    }

    private static void openAccount() {
        System.out.print("Customer ID: ");
        int customerId = sc.nextInt();
        System.out.print("Branch ID: ");
        int branchId = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Account Type (Savings/Current/FD): ");
        String type = sc.nextLine();
        System.out.print("Initial Balance: ");
        double balance = sc.nextDouble();
        sc.nextLine();

        Account acc = new Account();
        acc.setCustomerId(customerId);
        acc.setBranchId(branchId);
        acc.setAccountType(type);
        acc.setBalance(balance);

        if (accountDAO.openAccount(acc)) {
            System.out.println("Account opened successfully!");
        } else {
            System.out.println("Error opening account.");
        }
    }

    private static void closeAccount() {
        System.out.print("Account ID: ");
        int accountId = sc.nextInt();
        sc.nextLine();

        if (accountDAO.closeAccount(accountId)) {
            System.out.println("Account closed successfully!");
        } else {
            System.out.println("Error closing account.");
        }
    }

    private static void viewAccountDetails() {
        System.out.print("Account ID: ");
        int accountId = sc.nextInt();
        sc.nextLine();

        Account acc = accountDAO.getAccountById(accountId);
        if (acc != null) {
            System.out.println("Account ID: " + acc.getAccountId());
            System.out.println("Customer ID: " + acc.getCustomerId());
            System.out.println("Branch ID: " + acc.getBranchId());
            System.out.println("Account Type: " + acc.getAccountType());
            System.out.println("Balance: " + acc.getBalance());
            System.out.println("Opened On: " + acc.getOpenedOn());
            System.out.println("Status: " + acc.getStatus());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void changeAccountType() {
        System.out.print("Account ID: ");
        int accountId = sc.nextInt();
        sc.nextLine();
        System.out.print("New Account Type (Savings/Current/FD): ");
        String newType = sc.nextLine();

        if (accountDAO.changeAccountType(accountId, newType)) {
            System.out.println("Account type updated successfully!");
        } else {
            System.out.println("Error updating account type.");
        }
    }

    private static void checkBalance() {
        System.out.print("Account ID: ");
        int accountId = sc.nextInt();
        sc.nextLine();

        double balance = accountDAO.getBalance(accountId);
        System.out.println("Current Balance: " + balance);
    }
}
