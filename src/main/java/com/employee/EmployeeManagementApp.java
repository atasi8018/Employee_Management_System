package com.employee;

import com.employee.dao.EmployeeDAOImpl;
import com.employee.model.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagementApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EmployeeDAOImpl dao = new EmployeeDAOImpl();

        int choice = -1;

        while (choice != 0) {

            System.out.println("\n===== EMPLOYEE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Search Employee By ID");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Display Employees Department-wise");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        System.out.print("Enter employee name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter email: ");
                        String email = sc.nextLine();

                        System.out.print("Enter salary: ");
                        double salary =
                                Double.parseDouble(sc.nextLine());

                        System.out.print("Enter department ID: ");
                        int departmentId =
                                Integer.parseInt(sc.nextLine());

                        Employee employee = new Employee(
                                0, name, email, salary, departmentId);

                        if (dao.addEmployee(employee)) {
                            System.out.println(
                                    "Employee added successfully!");
                        } else {
                            System.out.println(
                                    "Failed to add employee.");
                        }
                        break;

                    case 2:
                        List<Employee> employees = dao.getAllEmployees();

                        if (employees.isEmpty()) {
                            System.out.println("No employees found.");
                        } else {
                            System.out.println("\nAll Employees:");
                            employees.forEach(System.out::println);
                        }
                        break;

                    case 3:
                        System.out.print("Enter employee ID: ");
                        int searchId =
                                Integer.parseInt(sc.nextLine());

                        Employee found =
                                dao.getEmployeeById(searchId);

                        if (found != null) {
                            System.out.println(found);
                        } else {
                            System.out.println("Employee not found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter employee ID to update: ");
                        int updateId =
                                Integer.parseInt(sc.nextLine());

                        Employee existing =
                                dao.getEmployeeById(updateId);

                        if (existing == null) {
                            System.out.println("Employee not found.");
                            break;
                        }

                        System.out.print("Enter new employee name: ");
                        existing.setEmployeeName(sc.nextLine());

                        System.out.print("Enter new email: ");
                        existing.setEmail(sc.nextLine());

                        System.out.print("Enter new salary: ");
                        existing.setSalary(
                                Double.parseDouble(sc.nextLine()));

                        System.out.print("Enter new department ID: ");
                        existing.setDepartmentId(
                                Integer.parseInt(sc.nextLine()));

                        if (dao.updateEmployee(existing)) {
                            System.out.println(
                                    "Employee updated successfully!");
                        } else {
                            System.out.println(
                                    "Employee was not updated.");
                        }
                        break;

                    case 5:
                        System.out.print("Enter employee ID to delete: ");
                        int deleteId =
                                Integer.parseInt(sc.nextLine());

                        if (dao.deleteEmployee(deleteId)) {
                            System.out.println(
                                    "Employee deleted successfully!");
                        } else {
                            System.out.println(
                                    "Employee not found.");
                        }
                        break;

                    case 6:
                        System.out.println(
                                "\nEmployees Department-wise:");
                        dao.displayEmployeesDepartmentWise();
                        break;

                    case 0:
                        System.out.println("Application closed.");
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid input. Please enter a valid number.");

            } catch (SQLException e) {
                System.out.println(
                        "Database error: " + e.getMessage());

            } catch (Exception e) {
                System.out.println(
                        "Error: " + e.getMessage());
            }
        }

        sc.close();
    }
}
