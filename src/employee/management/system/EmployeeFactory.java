/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.management.system;


public class EmployeeFactory {
    public Employee createEmployee(String type){
        switch (type){
            case "FullTime":
                return new FullTimeEmployee();
            case "PartTime":
                return new PartTimeEmployee();
            case "Contractor":
                return new Contractor();
            default:
                throw new IllegalArgumentException("Unknown employee type");
        }
    }
}
