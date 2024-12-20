/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.management.system;


public class DecoratorPatternDemo {
    public static void main(String[] args) {
        Employee basicEmployee = new BasicEmployee("John Doe");

        // Decorate the basic employee with a project assignment
        Employee projectAssignedEmployee = new ProjectAssignmentDecorator(basicEmployee, "AI Development");

        // Perform duties
        System.out.println("Basic Employee:");
        basicEmployee.performDuties();

        System.out.println("\nProject Assigned Employee:");
        projectAssignedEmployee.performDuties();
    }
}
