package com.bank.cli;

import com.bank.dao.LoginLogDAO;
import com.bank.model.LoginLog;

import java.util.Scanner;

public class LoginLogOperations {
    private static final LoginLogDAO loginLogDAO = new LoginLogDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void runLoginLogModule() {
        while (true) {
            System.out.println("\n===== Login Log Module =====");
            System.out.println("1. Log Login Attempt");
            System.out.println("2. Log Logout");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    logLoginAttempt();
                    break;
                case 2:
                    logLogout();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void logLoginAttempt() {
        System.out.print("User ID: ");
        int userId = sc.nextInt();
        sc.nextLine();
        System.out.print("Status (Success/Failed): ");
        String status = sc.nextLine();
        System.out.print("IP Address: ");
        String ip = sc.nextLine();

        LoginLog log = new LoginLog();
        log.setUserId(userId);
        log.setStatus(status);
        log.setIpAddress(ip);

        loginLogDAO.logLogin(log);
        System.out.println("Login attempt logged successfully!");
    }

    private static void logLogout() {
        System.out.print("User ID: ");
        int userId = sc.nextInt();
        sc.nextLine();

        loginLogDAO.logLogout(userId);
        System.out.println("Logout time updated successfully!");
    }
}
