/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.management.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Types;


public class EmployeeDAO {
    private Connection connection;

    public EmployeeDAO() {
        connection = DatabaseConnectionManager.getInstance().getConnection();
    }

    public void addEmployee(Employee employee) {
    try {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO employees (employee_id, name, phone_number, gender, department_type, employee_type, project_name) VALUES (?, ?, ?, ?, ?, ?, ?)");
        statement.setInt(1, employee.getEmployeeId());
        statement.setString(2, employee.getName());
        statement.setString(3, employee.getPhoneNumber());
        statement.setString(4, employee.getGender());
        statement.setString(5, employee.getDepartmentType());
        statement.setString(6, employee.getClass().getSimpleName());
        
        // If employee is decorated with a project assignment, include project name
        if (employee instanceof ProjectAssignmentDecorator) {
            ProjectAssignmentDecorator decorator = (ProjectAssignmentDecorator) employee;
            statement.setString(7, decorator.getProjectName());
        } else {
            statement.setNull(7, Types.VARCHAR);
        }

        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    public void assignProject(int employeeId, String projectName) {
    PreparedStatement statement = null;
    try {
        statement = connection.prepareStatement("UPDATE employees SET project_name = ? WHERE employee_id = ?");
        statement.setString(1, projectName);
        statement.setInt(2, employeeId);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Project assignment updated successfully for employee ID: " + employeeId);
        } else {
            System.out.println("No employee found with ID: " + employeeId);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

    public List<Employee> getAllEmployees() {
    List<Employee> employees = new ArrayList<>();
    try {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int employeeId = resultSet.getInt("employee_id");
            String name = resultSet.getString("name");
            String phoneNumber = resultSet.getString("phone_number");
            String gender = resultSet.getString("gender");
            String departmentType = resultSet.getString("department_type");
            String employeeType = resultSet.getString("employee_type");
            String projectName = resultSet.getString("project_name"); // Retrieve project name

            Employee employee = createEmployeeBasedOnType(employeeType);
            if (employee != null) {
                employee.setId(id);
                employee.setEmployeeId(employeeId);
                employee.setName(name);
                employee.setPhoneNumber(phoneNumber);
                employee.setGender(gender);
                employee.setDepartmentType(departmentType);

                // If project name is not null, wrap the employee with ProjectAssignmentDecorator
                if (projectName != null && !projectName.isEmpty()) {
                    employee = new ProjectAssignmentDecorator(employee, projectName);
                }

                employees.add(employee);
                System.out.println("Fetched employee from DB: " + name + ", ID: " + employeeId + ", Type: " + employeeType + ", Project: " + projectName);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return employees;
}
    
    // Helper method to create employee based on type 
    public Employee createEmployeeBasedOnType(String employeeType) {
    System.out.println("Creating employee of type: " + employeeType); // Debugging
    switch (employeeType) {
        case "FullTimeEmployee":
            return new FullTimeEmployee();
        case "PartTimeEmployee":
            return new PartTimeEmployee();
        case "Contractor":
            return new Contractor();
        default:
            System.err.println("Unknown employee type: " + employeeType);
            return null; // Should not happen
    }
}
    
    public void updateEmployee(int oldId, Employee employee) {
    PreparedStatement statement = null;
    try {
        System.out.println("Preparing to update employee with old ID: " + oldId + " and Employee ID: " + employee.getEmployeeId() + " and Name: " + employee.getName()); // Debugging
        statement = connection.prepareStatement(
            "UPDATE employees SET employee_id = ?, name = ?, phone_number = ?, gender = ?, department_type = ?, employee_type = ?, project_name = ? WHERE id = ?");
        statement.setInt(1, employee.getEmployeeId());
        statement.setString(2, employee.getName());
        statement.setString(3, employee.getPhoneNumber());
        statement.setString(4, employee.getGender());
        statement.setString(5, employee.getDepartmentType());
        
        // Ensure proper type handling for decorated employees
        String originalType = employee.getClass().getSimpleName();
        if (employee instanceof ProjectAssignmentDecorator) {
            originalType = ((ProjectAssignmentDecorator) employee).getOriginalEmployeeType();
        }
        statement.setString(6, originalType);

        // Handle project name
        if (employee instanceof ProjectAssignmentDecorator) {
            ProjectAssignmentDecorator decorator = (ProjectAssignmentDecorator) employee;
            statement.setString(7, decorator.getProjectName());
        } else {
            statement.setNull(7, Types.VARCHAR);
        }

        statement.setInt(8, oldId);
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing employee was updated successfully!");
        } else {
            System.out.println("No rows updated. Check if the ID exists.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

    public void deleteEmployee(int id) {
    PreparedStatement deleteAttendanceStmt = null;
    PreparedStatement deleteEmployeeStmt = null;
    try {
        // First, delete attendance records for the employee
        deleteAttendanceStmt = connection.prepareStatement("DELETE FROM attendance WHERE employee_id = ?");
        deleteAttendanceStmt.setInt(1, id);
        int attendanceDeleted = deleteAttendanceStmt.executeUpdate();
        
        // Then, delete the employee record
        deleteEmployeeStmt = connection.prepareStatement("DELETE FROM employees WHERE id = ?");
        deleteEmployeeStmt.setInt(1, id);
        int employeeDeleted = deleteEmployeeStmt.executeUpdate();
        
        if (employeeDeleted > 0) {
            System.out.println("Employee with ID " + id + " was deleted successfully along with " + attendanceDeleted + " attendance records.");
        } else {
            System.out.println("No employee found with ID " + id + ".");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (deleteAttendanceStmt != null) {
            try {
                deleteAttendanceStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (deleteEmployeeStmt != null) {
            try {
                deleteEmployeeStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
    
        public Employee getEmployeeByEmployeeId(int employeeId) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT e.*, p.base_salary FROM employees e LEFT JOIN payroll p ON e.employee_id = p.employee_id WHERE e.employee_id = ?");
            statement.setInt(1, employeeId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id"); // Retrieve auto-incremented ID
                String name = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phone_number");
                String gender = resultSet.getString("gender");
                String departmentType = resultSet.getString("department_type");
                String employeeType = resultSet.getString("employee_type");
                double baseSalary = resultSet.getDouble("base_salary");
                String projectName = resultSet.getString("project_name");

                Employee employee = createEmployeeBasedOnType(employeeType);
                if (employee != null) {
                    employee.setId(id); // Auto-incremented ID
                    employee.setEmployeeId(employeeId); // Manually set employee ID
                    employee.setName(name);
                    employee.setPhoneNumber(phoneNumber);
                    employee.setGender(gender);
                    employee.setDepartmentType(departmentType);
                    employee.setBaseSalary(baseSalary);
                    
                    // If project name is not null, wrap the employee with ProjectAssignmentDecorator
                    if(projectName != null && !projectName.isEmpty()){
                        employee = new ProjectAssignmentDecorator(employee, projectName);
                    }
                    return employee;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
       
        // Method to add attendance record
        public void addAttendance(int employeeId, String attendanceDate, String status) {
    PreparedStatement statement = null;
    try {
        statement = connection.prepareStatement("INSERT INTO attendance (employee_id, attendance_date, status) VALUES (?, ?, ?)");
        statement.setInt(1, employeeId);
        statement.setString(2, attendanceDate);
        statement.setString(3, status);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error adding attendance for employee ID: " + employeeId + ", Date: " + attendanceDate + ", Status: " + status);
    } finally {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
       
       // Method to retrieve attendance records
       public List<Attendance> getAttendanceRecords(int employeeId) {
    List<Attendance> attendanceRecords = new ArrayList<>();
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    try {
        statement = connection.prepareStatement("SELECT * FROM attendance WHERE employee_id = ?");
        statement.setInt(1, employeeId);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String date = resultSet.getString("attendance_date");
            String status = resultSet.getString("status");
            Attendance attendance = new Attendance(id, employeeId, date, status);
            attendanceRecords.add(attendance);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return attendanceRecords;
}
       
       public void addPayroll(int employeeId, double baseSalary) {
    PreparedStatement statement = null;
    try {
        statement = connection.prepareStatement("INSERT INTO payroll (employee_id, base_salary) VALUES (?, ?)");
        statement.setInt(1, employeeId);
        statement.setDouble(2, baseSalary);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error adding payroll for employee ID: " + employeeId + ", Base Salary: " + baseSalary);
    } finally {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
}
