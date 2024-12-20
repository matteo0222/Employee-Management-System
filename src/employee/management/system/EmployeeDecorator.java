/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.management.system;

/**
 *
 * @author HP
 */
public abstract class EmployeeDecorator extends Employee {
    protected Employee decoratedEmployee;
    
    public EmployeeDecorator (Employee decoratedEmployee){
        this.decoratedEmployee = decoratedEmployee;
    }

    @Override
    public void setAttendance(int attendance) {
        super.setAttendance(attendance); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getAttendance() {
        return super.getAttendance(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBaseSalary(double baseSalary) {
        super.setBaseSalary(baseSalary); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getBaseSalary() {
        return super.getBaseSalary(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDepartmentType(String departmentType) {
        super.setDepartmentType(departmentType); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDepartmentType() {
        return super.getDepartmentType(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setGender(String gender) {
        super.setGender(gender); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getGender() {
        return super.getGender(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        super.setPhoneNumber(phoneNumber); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEmployeeId(int employeeId) {
        super.setEmployeeId(employeeId); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getEmployeeId() {
        return super.getEmployeeId(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPhoneNumber() {
        return super.getPhoneNumber(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(int id) {
        super.setId(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setName(String name) {
        super.setName(name); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        return super.getName(); //To change body of generated methods, choose Tools | Templates.
    }
        
    
    
}
