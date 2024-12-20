/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.management.system;

import java.util.ArrayList;
import java.util.List;

public class Attendance implements Subject{
    private List<EmployeeObserver> observers = new ArrayList<>();
    private Employee employee;
    
    private int id;
    private int employeeId;
    private String date;
    private String status;
    
    public Attendance() {}

    public Attendance(int id, int employeeId, String date, String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.date = date;
        this.status = status;
    }
    
    // Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
     

    @Override
    public void addObserver(EmployeeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(EmployeeObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (EmployeeObserver observer : observers) {
            observer.update(employee);
        }
    }

    public void setEmployeeAttendance(Employee employee) {
        this.employee = employee;
        notifyObservers();
    }
}
