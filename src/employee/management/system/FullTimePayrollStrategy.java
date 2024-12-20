/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.management.system;


public class FullTimePayrollStrategy implements PayrollStrategy {

    @Override
    public double calculatePay(Employee employee) {
        // Full-time employee payroll calculation logic
        return employee.getBaseSalary();
    }
    
}
