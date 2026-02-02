package com.bank;

import com.bank.util.DBConnection;

import java.util.Scanner;

public class App {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Starting Bank Application...");

        DBConnection.createTables();

        System.out.println("System ready. All modules initialized.");


    while (true) {
        System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");
        System.out.println("1. Customer Module");
        System.out.println("2. Account Module");
        System.out.println("3. Transaction Module");
        System.out.println("4. Employee Module");
        System.out.println("5. Branch Module");
        System.out.println("6. Loan Module");
        System.out.println("7. Loan Payment Module");
        System.out.println("8. User Module");
        System.out.println("9. Login Log Module");
        System.out.println("10. Exit");
        System.out.print("Choose an option: ");

        int choice = sc.nextInt();
        sc.nextLine(); // consume newline

        switch (choice) {
            case 1:
                com.bank.cli.CustomerOperations.runCustomerModule();
                break;
            case 2:
                com.bank.cli.AccountOperations.runAccountModule();
                break;
            case 3:
                com.bank.cli.TransactionOperations.runTransactionModule();
                break;
            case 4:
                com.bank.cli.EmployeeOperations.runEmployeeModule();
                break;
            case 5:
                com.bank.cli.BranchOperations.runBranchModule();
                break;
            case 6:
                com.bank.cli.LoanOperations.runLoanModule();
                break;
            case 7:
                com.bank.cli.LoanPaymentOperations.runLoanPaymentModule();
                break;
            case 8:
                com.bank.cli.UserOperations.runUserModule();
                break;
            case 9:
                com.bank.cli.LoginLogOperations.runLoginLogModule();
                break;
            case 10:
                System.out.println("Exiting program...");
                System.exit(0);
            default:
                System.out.println("Invalid choice! Try again.");
        }
    }
}
}
