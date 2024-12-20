/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.management.system;


public class PartTimeEmployee extends Employee {
    public PartTimeEmployee(){
        
    }

    public PartTimeEmployee(String name, int employeeId, String phone, String gender, String department) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public  void performDuties(){
        System.out.println(name + " is performing part-time duties.");
    }
}
