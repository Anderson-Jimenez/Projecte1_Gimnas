/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projecte1_gimnas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/gym";
    private static final String USER = "phpmyadmin";
    private static final String PASSWORD = "LaP1n3d4Badalona@"; 
    public static Connection databaseLink; 
    


    public static Connection getConnection() throws ClassNotFoundException {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(URL,USER,PASSWORD);
        
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return databaseLink;
        
    }
}
