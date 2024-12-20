/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.management.system;


public class AttendanceObserver implements EmployeeObserver {
    @Override
    public void update(Employee employee) {
        // Handle the attendance update logic here
        System.out.println("Attendance updated for Employee ID: " + employee.getId());
    }
    
}
