/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.management.system;

import java.sql.Connection;


/**
 *
 * @author HP
 */
public class DatabaseConnectionTest {
    public static void main(String[] args) {
        Connection connection = DatabaseConnectionManager.getInstance().getConnection();
        if(connection != null) {
            System.out.println("Successfully connected to the database!");
        } else{
            System.out.println("Failed to connect to the database.");
        }
    }
}
