/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.management.system.ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import employee.management.system.Employee;
import employee.management.system.EmployeeDAO;
import employee.management.system.FullTimeEmployee;
import employee.management.system.PartTimeEmployee;
import employee.management.system.Contractor;
import employee.management.system.ProjectAssignmentDecorator;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.*;
import java.awt.*;



public class EmployeePanel extends JPanel {
    private JTextField nameField;
    private JTextField idField;
    private JTextField employeeIdField;
    private JTextField phoneField;
    private JComboBox<String> genderComboBox;
    private JTable employeeTable;
    private EmployeeDAO employeeDAO;
    private JRadioButton fullTimeButton;
    private JRadioButton partTimeButton;
    private JRadioButton contractorButton;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JRadioButton hrButton;
    private JRadioButton financeButton;
    private JRadioButton itButton;
    private JRadioButton marketingButton;
    private JTextField employeeNameField;
    private JTextField projectNameField; // New field for project name
    private JTextArea displayArea;
    private Employee currentEmployee;

    public EmployeePanel() {
        // Initialize the DAO
        employeeDAO = new EmployeeDAO();

        // Set layout for the main panel
        setLayout(new BorderLayout());

        // Create input panel with GridBagLayout
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add name label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        nameField = new JTextField(15);
        inputPanel.add(nameField, gbc);

        // Add employee ID label and field (for manually setting employee_id)
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Employee ID:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        employeeIdField = new JTextField(15);
        inputPanel.add(employeeIdField, gbc);

        // Add phone number label and field
        gbc.gridx = 0;
        gbc.gridy = 2; // Updated the y-coordinate accordingly
        inputPanel.add(new JLabel("Phone Number:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        phoneField = new JTextField(15);
        inputPanel.add(phoneField, gbc);

        // Add gender label and combo box
        gbc.gridx = 0;
        gbc.gridy = 3; // Updated the y-coordinate accordingly
        inputPanel.add(new JLabel("Gender:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        String[] genders = {"Male", "Female"};
        genderComboBox = new JComboBox<>(genders);
        inputPanel.add(genderComboBox, gbc);

        // Add radio buttons for employee type
        fullTimeButton = new JRadioButton("Full-Time");
        partTimeButton = new JRadioButton("Part-Time");
        contractorButton = new JRadioButton("Contractor");

        // Group the radio buttons
        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(fullTimeButton);
        typeGroup.add(partTimeButton);
        typeGroup.add(contractorButton);

        // Set default selection
        fullTimeButton.setSelected(true);

        // Add employee type radio buttons
        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Employee Type:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        inputPanel.add(fullTimeButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        inputPanel.add(partTimeButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 6;
        inputPanel.add(contractorButton, gbc);
        
        // Add department type radio buttons
        gbc.gridx = 0;
        gbc.gridy = 7;
        inputPanel.add(new JLabel("Department Type:"), gbc);

        hrButton = new JRadioButton("HR");
        financeButton = new JRadioButton("Finance");
        itButton = new JRadioButton("IT");
        marketingButton = new JRadioButton("Marketing");
        
        // Group the radio buttons
        ButtonGroup departmentGroup = new ButtonGroup();
        departmentGroup.add(hrButton);
        departmentGroup.add(financeButton);
        departmentGroup.add(itButton);
        departmentGroup.add(marketingButton);

        // Set default selection
        hrButton.setSelected(true);

        gbc.gridx = 1;
        gbc.gridy = 7;
        inputPanel.add(hrButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        inputPanel.add(financeButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        inputPanel.add(itButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 10;
        inputPanel.add(marketingButton, gbc);
        
        // Add project name label and field
        gbc.gridx = 0;
        gbc.gridy = 12;
        inputPanel.add(new JLabel("Project Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 12;
        projectNameField = new JTextField(15);
        inputPanel.add(projectNameField, gbc);

        // Add assign project button
        JButton assignProjectButton = new JButton("Assign Project");
        assignProjectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                assignProject();
            }
        });
        
        displayArea = new JTextArea(15, 50);
        displayArea.setEditable(false);

        gbc.gridx = 2;
        gbc.gridy = 12;
        inputPanel.add(assignProjectButton, gbc);
        
        // Add action buttons
        addButton = new JButton("Add Employee");
        updateButton = new JButton("Update Employee");
        deleteButton = new JButton("Delete Employee");

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            addEmployee();
        }
    });

        updateButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            updateEmployee();
        }
    });

    deleteButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            deleteEmployee();
        }
    });

        // Add buttons to the input panel
        gbc.gridx = 0;
        gbc.gridy = 11;
        inputPanel.add(addButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 11;
        inputPanel.add(updateButton, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 11;
        inputPanel.add(deleteButton, gbc);

        // Add input panel to the North region of the BorderLayout
        add(inputPanel, BorderLayout.NORTH);

        // Create table and add it to a scroll pane
        employeeTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load initial data
        loadEmployees();
    }

    // Method to add a new employee
    private void addEmployee() {
    String name = nameField.getText();
    String employeeIdStr = employeeIdField.getText(); // Employee ID instead of ID
    String phone = phoneField.getText();
    String gender = (String) genderComboBox.getSelectedItem();
    String department = getSelectedDepartmentType();

    if (!employeeIdStr.isEmpty()) {
        try {
            int employeeId = Integer.parseInt(employeeIdStr);
            Employee employee = createEmployee(); // Create employee based on the selected type
            employee.setEmployeeId(employeeId);
            employee.setName(name);
            employee.setPhoneNumber(phone);
            employee.setGender(gender);
            employee.setDepartmentType(department);

            // Check if a project needs to be assigned
            String projectName = projectNameField.getText();
            if (!projectName.isEmpty()) {
                employee = new ProjectAssignmentDecorator(employee, projectName); // Assign project using Decorator
            }

            employeeDAO.addEmployee(employee); // Add employee to the database
            loadEmployees(); // Refresh the table
            displayArea.append("Added Employee: " + name + "\n");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Employee ID format. Please enter a valid number.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Employee ID field cannot be empty.");
    }
}

    // Method to update an existing employee
    private void updateEmployee() {
    int selectedRow = employeeTable.getSelectedRow();
    if (selectedRow >= 0) {
        String oldIdStr = employeeTable.getValueAt(selectedRow, 0).toString();
        if (!oldIdStr.isEmpty()) {
            try {
                int oldId = Integer.parseInt(oldIdStr);
                String employeeIdStr = employeeIdField.getText();
                String phone = phoneField.getText();
                String gender = (String) genderComboBox.getSelectedItem();
                String department = getSelectedDepartmentType();
                if (!employeeIdStr.isEmpty()) {
                    int employeeId = Integer.parseInt(employeeIdStr);
                    String name = nameField.getText();
                    String employeeType = employeeTable.getValueAt(selectedRow, 6).toString(); // Retrieve type from table

                    // Create employee based on the original type
                    Employee employee = employeeDAO.createEmployeeBasedOnType(employeeType);
                    if (employee == null) {
                        JOptionPane.showMessageDialog(this, "Unknown employee type. Cannot update employee.");
                        return;
                    }

                    employee.setId(oldId); // Use oldId as it should not change
                    employee.setEmployeeId(employeeId);
                    employee.setName(name);
                    employee.setPhoneNumber(phone);
                    employee.setGender(gender);
                    employee.setDepartmentType(department);

                    // Check if a project needs to be assigned
                    String projectName = projectNameField.getText();
                    if (!projectName.isEmpty()) {
                        employee = new ProjectAssignmentDecorator(employee, projectName); // Assign project using Decorator
                    }

                    System.out.println("Attempting to update employee with ID " + oldId + " and Name: " + name);
                    employeeDAO.updateEmployee(oldId, employee);
                    System.out.println("Update operation invoked.");
                    loadEmployees(); // Refresh the table
                    System.out.println("Table refreshed.");

                } else {
                    JOptionPane.showMessageDialog(this, "Employee ID field cannot be empty.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid Employee ID format. Please enter a valid number.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Old ID field cannot be empty.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Select an employee to update.");
    }
}

    // Method to delete an employee
    private void deleteEmployee() {
    int selectedRow = employeeTable.getSelectedRow();
    if (selectedRow >= 0) {
        int id = (int) employeeTable.getValueAt(selectedRow, 0);
        employeeDAO.deleteEmployee(id);
        loadEmployees(); // Refresh the table
        displayArea.append("Deleted Employee with ID: " + id + "\n");
    } else {
        JOptionPane.showMessageDialog(this, "Select an employee to delete.");
    }
}

    // Method to load employees into the table
    private void loadEmployees() {
    List<Employee> employees = employeeDAO.getAllEmployees();
    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Employee ID", "Name", "Phone Number", "Gender", "Department", "Type", "Project"}, 0);
    for (Employee employee : employees) {
        String employeeType = employee.getClass().getSimpleName();
        String project = "None";
        if (employee instanceof ProjectAssignmentDecorator) {
            ProjectAssignmentDecorator decorator = (ProjectAssignmentDecorator) employee;
            employeeType = decorator.getOriginalEmployeeType(); // Get the original employee type
            project = decorator.getProjectName();
        }
        model.addRow(new Object[]{
            employee.getId(),
            employee.getEmployeeId(),
            employee.getName(),
            employee.getPhoneNumber(),
            employee.getGender(),
            employee.getDepartmentType(),
            employeeType,
            project
        });
    }
    employeeTable.setModel(model);
}
    
    // Method to create an employee based on the selected type
    private Employee createEmployee() {
        if (fullTimeButton.isSelected()){
            return new FullTimeEmployee();
        } else if (partTimeButton.isSelected()){
            return new PartTimeEmployee();
        } else if (contractorButton.isSelected()){
            return new Contractor();
        }
        return null; // Default case (should not happen)
    }
    
    // Get selected department type
    private String getSelectedDepartmentType() {
    if (hrButton.isSelected()) {
        return "HR";
    } else if (financeButton.isSelected()) {
        return "Finance";
    } else if (itButton.isSelected()) {
        return "IT";
    } else if (marketingButton.isSelected()) {
        return "Marketing";
    }
    return null; // Default case (should not happen)
}
    
    private void assignProject() {
    int selectedRow = employeeTable.getSelectedRow();
    if (selectedRow >= 0) {
        String employeeIdStr = employeeTable.getValueAt(selectedRow, 1).toString();
        String projectName = projectNameField.getText();

        if (!projectName.isEmpty() && !employeeIdStr.isEmpty()) {
            try {
                int employeeId = Integer.parseInt(employeeIdStr);
                Employee employee = employeeDAO.getEmployeeByEmployeeId(employeeId); // Fetch employee details

                if (employee != null) {
                    employee = new ProjectAssignmentDecorator(employee, projectName); // Assign project using Decorator
                    employeeDAO.assignProject(employeeId, projectName); // Update the project in the database
                    displayArea.append(employee.getName() + " assigned to project: " + projectName + "\n");
                    loadEmployees(); // Refresh the table to reflect changes
                } else {
                    JOptionPane.showMessageDialog(this, "Employee not found.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid Employee ID format. Please enter a valid number.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please provide a project name.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Select an employee to assign a project.");
    }
}
        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
