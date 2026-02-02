package com.bank.cli;

import com.bank.dao.LoanDAO;
import com.bank.model.Loan;

import java.util.Scanner;

public class LoanOperations {
    private static final LoanDAO loanDAO = new LoanDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void runLoanModule() {
        while (true) {
            System.out.println("\n===== Loan Module =====");
            System.out.println("1. Apply Loan");
            System.out.println("2. Approve / Reject Loan");
            System.out.println("3. Calculate EMI");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    applyLoan();
                    break;
                case 2:
                    updateLoanStatus();
                    break;
                case 3:
                    calculateEmi();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void applyLoan() {
        System.out.print("Customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine();
        System.out.print("Loan Type (Home/Personal/Vehicle): ");
        String type = sc.nextLine();
        System.out.print("Amount: ");
        double amount = sc.nextDouble();
        System.out.print("Interest Rate (%): ");
        double rate = sc.nextDouble();
        System.out.print("Tenure (months): ");
        int tenure = sc.nextInt();
        sc.nextLine();

        Loan loan = new Loan();
        loan.setCustomerId(customerId);
        loan.setLoanType(type);
        loan.setAmount(amount);
        loan.setInterestRate(rate);
        loan.setTenureMonths(tenure);

        double emi = loanDAO.calculateEmi(amount, rate, tenure);
        loan.setEmiAmount(emi);

        if (loanDAO.applyLoan(loan)) {
            System.out.println("Loan applied successfully! EMI: " + emi);
        } else {
            System.out.println("Error applying loan.");
        }
    }

    private static void updateLoanStatus() {
        System.out.print("Loan ID: ");
        int loanId = sc.nextInt();
        sc.nextLine();
        System.out.print("Status (Approved/Rejected): ");
        String status = sc.nextLine();

        if (loanDAO.updateLoanStatus(loanId, status)) {
            System.out.println("Loan status updated to " + status);
        } else {
            System.out.println("Error updating loan status.");
        }
    }

    private static void calculateEmi() {
        System.out.print("Amount: ");
        double amount = sc.nextDouble();
        System.out.print("Interest Rate (%): ");
        double rate = sc.nextDouble();
        System.out.print("Tenure (months): ");
        int tenure = sc.nextInt();
        sc.nextLine();

        double emi = loanDAO.calculateEmi(amount, rate, tenure);
        System.out.println("Calculated EMI: " + emi);
    }
}
