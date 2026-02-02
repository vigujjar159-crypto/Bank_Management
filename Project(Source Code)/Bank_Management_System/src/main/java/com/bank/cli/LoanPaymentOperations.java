package com.bank.cli;

import com.bank.dao.LoanPaymentDAO;
import com.bank.model.LoanPayment;

import java.util.Scanner;

public class LoanPaymentOperations {
    private static final LoanPaymentDAO paymentDAO = new LoanPaymentDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void runLoanPaymentModule() {
        while (true) {
            System.out.println("\n===== Loan Payment Module =====");
            System.out.println("1. Pay EMI");
            System.out.println("2. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    payEmi();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void payEmi() {
        System.out.print("Loan ID: ");
        int loanId = sc.nextInt();
        System.out.print("Amount Paid: ");
        double amountPaid = sc.nextDouble();
        System.out.print("Remaining Amount: ");
        double remaining = sc.nextDouble();
        sc.nextLine(); // consume newline

        LoanPayment payment = new LoanPayment();
        payment.setLoanId(loanId);
        payment.setAmountPaid(amountPaid);
        payment.setRemainingAmount(remaining);

        if (paymentDAO.payEmi(payment)) {
            System.out.println("EMI payment successful!");
        } else {
            System.out.println("Error in EMI payment.");
        }
    }
}
