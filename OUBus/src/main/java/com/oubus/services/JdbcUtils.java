/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.services;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;


 /*
 * @author PC
 */
public class JdbcUtils {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JdbcUtils.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    public static Connection getConn() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost/oubus", "root", "Admin@123");
    }
}
