/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.management.system.ui;


import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class MainFrame extends JFrame {
    public MainFrame(){
        setTitle("Employee Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Initialize components
        initializeComponents();
    }
    
    private void initializeComponents(){
        // Create panels
        EmployeePanel employeePanel = new EmployeePanel();
        AttendancePanel attendancePanel = new AttendancePanel();
        PayrollPanel payrollPanel = new PayrollPanel();
        
        // Create a tabbed pane to switch between panels
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Employees", employeePanel);
        tabbedPane.addTab("Attendance", attendancePanel);
        tabbedPane.addTab("Payroll", payrollPanel);
        
        // Use a layout manager to arrange components
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    } 
}
