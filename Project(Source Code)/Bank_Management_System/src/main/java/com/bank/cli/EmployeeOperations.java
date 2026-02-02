package com.bank.cli;

import com.bank.dao.EmployeeDAO;
import com.bank.model.Employee;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class EmployeeOperations {
    private static final EmployeeDAO empDAO = new EmployeeDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void runEmployeeModule() {
        while (true) {
            System.out.println("\n===== Employee Module =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee Details");
            System.out.println("3. Activate/Deactivate Employee");
            System.out.println("4. Get Employee by ID");
            System.out.println("5. List Employees by Branch");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    updateEmployee();
                    break;
                case 3:
                    updateEmployeeStatus();
                    break;
                case 4:
                    getEmployeeById();
                    break;
                case 5:
                    listEmployeesByBranch();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addEmployee() {
        System.out.print("Branch ID: ");
        int branchId = sc.nextInt();
        sc.nextLine();
        System.out.print("First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Role (Admin/Manager/Staff): ");
        String role = sc.nextLine();
        System.out.print("Salary: ");
        double salary = sc.nextDouble();
        sc.nextLine();

        Employee e = new Employee();
        e.setBranchId(branchId);
        e.setFirstName(firstName);
        e.setEmail(email);
        e.setPhone(phone);
        e.setRole(role);
        e.setSalary(salary);

        if (empDAO.addEmployee(e)) {
            System.out.println("Employee added successfully!");
        } else {
            System.out.println("Error adding employee.");
        }
    }

    private static void updateEmployee() {
        System.out.print("Employee ID: ");
        int empId = sc.nextInt();
        sc.nextLine();
        System.out.print("First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Role (Admin/Manager/Staff): ");
        String role = sc.nextLine();
        System.out.print("Salary: ");
        double salary = sc.nextDouble();
        sc.nextLine();

        Employee e = new Employee();
        e.setEmployeeId(empId);
        e.setFirstName(firstName);
        e.setPhone(phone);
        e.setRole(role);
        e.setSalary(salary);

        if (empDAO.updateEmployee(e)) {
            System.out.println("Employee details updated successfully!");
        } else {
            System.out.println("Error updating employee details.");
        }
    }

    private static void updateEmployeeStatus() {
        System.out.print("Employee ID: ");
        int empId = sc.nextInt();
        sc.nextLine();
        System.out.print("Status (Active/Inactive): ");
        String status = sc.nextLine();

        if (empDAO.updateStatus(empId, status)) {
            System.out.println("Employee status updated!");
        } else {
            System.out.println("Error updating status.");
        }
    }

    private static void getEmployeeById() {
        System.out.print("Employee ID: ");
        int empId = sc.nextInt();
        sc.nextLine();

        Employee e = empDAO.getEmployeeById(empId);
        if (e != null) {
            System.out.println("Employee ID: " + e.getEmployeeId());
            System.out.println("Name: " + e.getFirstName());
            System.out.println("Email: " + e.getEmail());
            System.out.println("Phone: " + e.getPhone());
            System.out.println("Role: " + e.getRole());
            System.out.println("Salary: " + e.getSalary());
            System.out.println("Date Joined: " + e.getDateJoined());
            System.out.println("Status: " + e.getStatus());
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void listEmployeesByBranch() {
        System.out.print("Branch ID: ");
        int branchId = sc.nextInt();
        sc.nextLine();

        List<Employee> list = empDAO.getEmployeesByBranch(branchId);
        if (list.isEmpty()) {
            System.out.println("No employees found in this branch.");
            return;
        }

        System.out.println("\n--- Employees List ---");
        for (Employee e : list) {
            System.out.println("ID: " + e.getEmployeeId() +
                    ", Name: " + e.getFirstName() +
                    ", Role: " + e.getRole() +
                    ", Status: " + e.getStatus());
        }
    }
}
