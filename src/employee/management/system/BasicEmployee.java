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
public class BasicEmployee extends Employee{
    public BasicEmployee(String name){
        this.name = name;
    }
    
    @Override
    public  void performDuties(){
        System.out.println(name + " is performing basic duties.");
    }
}
