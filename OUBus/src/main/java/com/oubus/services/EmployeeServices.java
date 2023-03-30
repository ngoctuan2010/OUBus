/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.services;

import com.oubus.pojo.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class EmployeeServices {
    
     public static Employee getEmployeeByID(int ID) throws SQLException{
        Employee e = new Employee();
        try(Connection cnn = JdbcUtils.getConn()){
            
            String sql = "SELECT * FROM employee WHERE employeeID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, ID);
            
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                e.setEmployeeID(rs.getString("employeeID"));
                e.setName(rs.getString("name"));
                e.setSex(rs.getBoolean("sex"));
                e.setDateOfBirth(rs.getDate("DateOfBirth"));
                e.setNationality(rs.getString("nationality"));
                e.setNationalID(rs.getString("nationalID"));
                e.setAddress(rs.getString("address"));
                e.setEmail(rs.getString("email"));
                e.setTelephone(rs.getString("phoneNumber"));
                e.setPosition(rs.getString("position"));
              
            }  
            return e;
        }

    }
}
