package com.bank.cli;

import com.bank.dao.BranchDAO;
import com.bank.model.Branch;

import java.util.List;
import java.util.Scanner;

public class BranchOperations {
    private static final BranchDAO branchDAO = new BranchDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void runBranchModule() {
        while (true) {
            System.out.println("\n===== Branch Module =====");
            System.out.println("1. Add Branch");
            System.out.println("2. Update Branch");
            System.out.println("3. Get Branch by ID");
            System.out.println("4. List All Branches");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addBranch();
                    break;
                case 2:
                    updateBranch();
                    break;
                case 3:
                    getBranchById();
                    break;
                case 4:
                    listAllBranches();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addBranch() {
        System.out.print("Branch Name: ");
        String name = sc.nextLine();
        System.out.print("IFSC Code: ");
        String ifsc = sc.nextLine();
        System.out.print("Address: ");
        String address = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();

        Branch b = new Branch();
        b.setBranchName(name);
        b.setIfscCode(ifsc);
        b.setAddress(address);
        b.setPhone(phone);

        if (branchDAO.addBranch(b)) {
            System.out.println("Branch added successfully!");
        } else {
            System.out.println("Error adding branch.");
        }
    }

    private static void updateBranch() {
        System.out.print("Branch ID: ");
        int branchId = sc.nextInt();
        sc.nextLine();
        System.out.print("Branch Name: ");
        String name = sc.nextLine();
        System.out.print("Address: ");
        String address = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();

        Branch b = new Branch();
        b.setBranchId(branchId);
        b.setBranchName(name);
        b.setAddress(address);
        b.setPhone(phone);

        if (branchDAO.updateBranch(b)) {
            System.out.println("Branch updated successfully!");
        } else {
            System.out.println("Error updating branch.");
        }
    }

    private static void getBranchById() {
        System.out.print("Branch ID: ");
        int branchId = sc.nextInt();
        sc.nextLine();

        Branch b = branchDAO.getBranchById(branchId);
        if (b != null) {
            System.out.println("ID: " + b.getBranchId());
            System.out.println("Name: " + b.getBranchName());
            System.out.println("IFSC: " + b.getIfscCode());
            System.out.println("Address: " + b.getAddress());
            System.out.println("Phone: " + b.getPhone());
        } else {
            System.out.println("Branch not found.");
        }
    }

    private static void listAllBranches() {
        List<Branch> list = branchDAO.getAllBranches();
        if (list.isEmpty()) {
            System.out.println("No branches found.");
            return;
        }

        System.out.println("\n--- Branches List ---");
        for (Branch b : list) {
            System.out.println("ID: " + b.getBranchId() +
                    ", Name: " + b.getBranchName() +
                    ", IFSC: " + b.getIfscCode() +
                    ", Phone: " + b.getPhone());
        }
    }
}
