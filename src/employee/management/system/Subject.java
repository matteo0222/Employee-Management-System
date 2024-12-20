/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.management.system;


public interface Subject {
    void addObserver(EmployeeObserver observer);
    void removeObserver(EmployeeObserver observer);
    void notifyObservers();
}
