/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atmmachinegui;



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3307/bank";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "rohan123";
    
    public static Connection getConnection() throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("Exception loading driver");
        }
        return DriverManager.getConnection(URL , USERNAME , PASSWORD);  
    }
}
