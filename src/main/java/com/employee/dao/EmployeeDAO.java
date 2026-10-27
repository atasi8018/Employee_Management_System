package com.employee.dao;

import com.employee.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    boolean addEmployee(Employee employee)
            throws SQLException;

    List<Employee> getAllEmployees()
            throws SQLException;

    Employee getEmployeeById(int employeeId)
            throws Exception;

    boolean updateEmployee(Employee employee)
            throws Exception;

    boolean deleteEmployee(int employeeId)
            throws SQLException;
    void displayEmployeesDepartmentWise() throws SQLException;
}
