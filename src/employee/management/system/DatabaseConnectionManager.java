/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;
    private Connection connection;
    
    private DatabaseConnectionManager (){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/userData", "root", "");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static DatabaseConnectionManager getInstance(){
        if (instance == null){
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return connection;
    }
}
