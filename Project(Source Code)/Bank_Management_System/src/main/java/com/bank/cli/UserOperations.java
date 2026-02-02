package com.bank.cli;

import com.bank.dao.UserDAO;
import com.bank.model.User;

import java.util.Scanner;

public class UserOperations {
    private static final UserDAO userDAO = new UserDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void runUserModule() {
        while (true) {
            System.out.println("\n===== User Module =====");
            System.out.println("1. Create User");
            System.out.println("2. Find User by Username");
            System.out.println("3. Update Last Login");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    findUser();
                    break;
                case 3:
                    updateLastLogin();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void createUser() {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password Hash: ");
        String passwordHash = sc.nextLine();
        System.out.print("Role (Customer/Employee/Admin): ");
        String role = sc.nextLine();
        System.out.print("Linked Customer ID (or 0 if none): ");
        int linkedCustomerId = sc.nextInt();
        System.out.print("Linked Employee ID (or 0 if none): ");
        int linkedEmployeeId = sc.nextInt();
        sc.nextLine(); // consume newline

        User u = new User();
        u.setUsername(username);
        u.setPasswordHash(passwordHash);
        u.setRole(role);
        u.setLinkedCustomerId(linkedCustomerId == 0 ? null : linkedCustomerId);
        u.setLinkedEmployeeId(linkedEmployeeId == 0 ? null : linkedEmployeeId);

        if (userDAO.createUser(u)) {
            System.out.println("User created successfully!");
        } else {
            System.out.println("Error creating user.");
        }
    }

    private static void findUser() {
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        User u = userDAO.findByUsername(username);
        if (u != null) {
            System.out.println("User ID: " + u.getUserId());
            System.out.println("Username: " + u.getUsername());
            System.out.println("Role: " + u.getRole());
            System.out.println("Linked Customer ID: " + u.getLinkedCustomerId());
            System.out.println("Linked Employee ID: " + u.getLinkedEmployeeId());
            System.out.println("Last Login: " + u.getLastLogin());
        } else {
            System.out.println("User not found.");
        }
    }

    private static void updateLastLogin() {
        System.out.print("Enter User ID: ");
        int userId = sc.nextInt();
        sc.nextLine(); // consume newline
        userDAO.updateLastLogin(userId);
        System.out.println("Last login updated to current timestamp.");
    }
}
