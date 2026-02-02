package com.bank.cli;

import com.bank.dao.CustomerDAO;
import com.bank.model.Customer;

import java.sql.Date;
import java.util.Scanner;

public class CustomerOperations {
    private static final CustomerDAO customerDAO = new CustomerDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void runCustomerModule() {
        while (true) {
            System.out.println("\n===== Customer Module =====");
            System.out.println("1. Register Customer");
            System.out.println("2. Update Customer Address");
            System.out.println("3. Update KYC Status");
            System.out.println("4. Activate/Deactivate Customer");
            System.out.println("5. Search Customer by Phone/Email");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    updateCustomerAddress();
                    break;
                case 3:
                    updateKycStatus();
                    break;
                case 4:
                    updateCustomerStatus();
                    break;
                case 5:
                    searchCustomer();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
                    break;
            }
        }
    }

    private static void registerCustomer() {
        System.out.print("First Name: ");
        String firstName = sc.nextLine();
        System.out.print("DOB (yyyy-mm-dd): ");
        Date dob = Date.valueOf(sc.nextLine());
        System.out.print("Gender: ");
        String gender = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Address: ");
        String address = sc.nextLine();

        Customer c = new Customer();
        c.setFirstName(firstName);
        c.setDob(dob);
        c.setGender(gender);
        c.setPhone(phone);
        c.setEmail(email);
        c.setAddress(address);

        if (customerDAO.registerCustomer(c)) {
            System.out.println("Customer registered successfully!");
        } else {
            System.out.println("Error registering customer.");
        }
    }

    private static void updateCustomerAddress() {
        System.out.print("Customer ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("New Address: ");
        String address = sc.nextLine();

        if (customerDAO.updateProfile(id, address)) {
            System.out.println("Address updated successfully!");
        } else {
            System.out.println("Error updating address.");
        }
    }

    private static void updateKycStatus() {
        System.out.print("Customer ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("KYC Status (Pending/Verified/Rejected): ");
        String status = sc.nextLine();

        if (customerDAO.updateKycStatus(id, status)) {
            System.out.println("KYC status updated!");
        } else {
            System.out.println("Error updating KYC status.");
        }
    }

    private static void updateCustomerStatus() {
        System.out.print("Customer ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Status (Active/Inactive): ");
        String status = sc.nextLine();

        if (customerDAO.updateCustomerStatus(id, status)) {
            System.out.println("Customer status updated!");
        } else {
            System.out.println("Error updating status.");
        }
    }

    private static void searchCustomer() {
        System.out.print("Phone or Email: ");
        String input = sc.nextLine();

        Customer c = customerDAO.findCustomer(input);
        if (c != null) {
            System.out.println("Customer ID: " + c.getCustomerId());
            System.out.println("Name: " + c.getFirstName());
            System.out.println("DOB: " + c.getDob());
            System.out.println("Gender: " + c.getGender());
            System.out.println("Phone: " + c.getPhone());
            System.out.println("Email: " + c.getEmail());
            System.out.println("Address: " + c.getAddress());
            System.out.println("KYC Status: " + c.getKycStatus());
            System.out.println("Created At: " + c.getCreatedAt());
            System.out.println("Status: " + c.getStatus());
        } else {
            System.out.println("Customer not found.");
        }
    }
}
