/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.management.system;


public class PayrollSystem {
    private static PayrollSystem instance;
    
    private PayrollSystem() {
        // private constructor
    }
    
    public static PayrollSystem getInstance(){
        if (instance == null){
            instance = new PayrollSystem();
        }
        return instance;
    }
    
     // Payroll processing methods 
    public double processPayroll(Employee employee, PayrollStrategy strategy){
        try {
            return strategy.calculatePay(employee);
        } catch (Exception e) {
            // Handle exception (e.g., logging)
            e.printStackTrace();
            return 0; // return a default value or handle appropriately
        }
    }
}
