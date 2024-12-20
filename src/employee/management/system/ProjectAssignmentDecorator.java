/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.management.system;


public class ProjectAssignmentDecorator extends EmployeeDecorator {
    private String projectName;

    public ProjectAssignmentDecorator(Employee decoratedEmployee, String projectName) {
        super(decoratedEmployee);
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    public String getOriginalEmployeeType() {
        return decoratedEmployee.getClass().getSimpleName();
    }
    
    public Employee getOriginalEmployee(){
        return decoratedEmployee;
    }
    
    @Override
    public int getId() {
        return decoratedEmployee.getId();
    }

    @Override
    public int getEmployeeId() {
        return decoratedEmployee.getEmployeeId();
    }

    @Override
    public String getName() {
        return decoratedEmployee.getName();
    }

    @Override
    public String getPhoneNumber() {
        return decoratedEmployee.getPhoneNumber();
    }

    @Override
    public String getGender() {
        return decoratedEmployee.getGender();
    }

    @Override
    public String getDepartmentType() {
        return decoratedEmployee.getDepartmentType();
    }

    @Override
    public void performDuties() {
        decoratedEmployee.performDuties();
        assignProject();
    }

    private void assignProject() {
        System.out.println(decoratedEmployee.getName() + " is assigned to project: " + projectName);
    }
}
