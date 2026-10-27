
package com.employee.dao;

import com.employee.model.Employee;
import com.employee.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public boolean addEmployee(Employee employee) throws SQLException {

        String query = "INSERT INTO employees " +
                "(employeeName, email, salary, departmentId) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, employee.getEmployeeName());
            ps.setString(2, employee.getEmail());
            ps.setDouble(3, employee.getSalary());
            ps.setInt(4, employee.getDepartmentId());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Employee> getAllEmployees() throws SQLException {

        String query = "SELECT * FROM employees";
        List<Employee> employees = new ArrayList<>();

        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet res = ps.executeQuery()) {

            while (res.next()) {
                Employee employee = new Employee();

                employee.setEmployeeId(res.getInt("employeeId"));
                employee.setEmployeeName(res.getString("employeeName"));
                employee.setEmail(res.getString("email"));
                employee.setSalary(res.getDouble("salary"));
                employee.setDepartmentId(res.getInt("departmentId"));

                employees.add(employee);
            }
        }

        return employees;
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws Exception {

        String query =
                "SELECT * FROM employees WHERE employeeId = ?";

        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, employeeId);

            try (ResultSet res = ps.executeQuery()) {
                if (res.next()) {
                    Employee employee = new Employee();

                    employee.setEmployeeId(res.getInt("employeeId"));
                    employee.setEmployeeName(res.getString("employeeName"));
                    employee.setEmail(res.getString("email"));
                    employee.setSalary(res.getDouble("salary"));
                    employee.setDepartmentId(res.getInt("departmentId"));

                    return employee;
                }
            }
        }

        return null;
    }

    @Override
    public boolean updateEmployee(Employee employee) throws Exception {

        String query = "UPDATE employees SET employeeName = ?, " +
                "email = ?, salary = ?, departmentId = ? " +
                "WHERE employeeId = ?";

        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, employee.getEmployeeName());
            ps.setString(2, employee.getEmail());
            ps.setDouble(3, employee.getSalary());
            ps.setInt(4, employee.getDepartmentId());
            ps.setInt(5, employee.getEmployeeId());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteEmployee(int employeeId) throws SQLException {

        String query =
                "DELETE FROM employees WHERE employeeId = ?";

        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, employeeId);

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public void displayEmployeesDepartmentWise() throws SQLException {

        String query = "SELECT e.employeeId, e.employeeName, " +
                "e.email, e.salary, d.departmentName " +
                "FROM employees e " +
                "INNER JOIN departments d " +
                "ON e.departmentId = d.departmentId " +
                "ORDER BY d.departmentName";

        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet res = ps.executeQuery()) {

            boolean found = false;

            while (res.next()) {
                found = true;

                System.out.println(
                        "ID: " + res.getInt("employeeId") +
                                " | Name: " + res.getString("employeeName") +
                                " | Email: " + res.getString("email") +
                                " | Salary: " + res.getDouble("salary") +
                                " | Department: " + res.getString("departmentName")
                );
            }

            if (!found) {
                System.out.println("No Employees Found");
            }
        }
    }
}
