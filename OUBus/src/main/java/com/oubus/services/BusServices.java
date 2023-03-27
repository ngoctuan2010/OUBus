/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class BusServices {
    public static String getLicensePlateByID(int ID){
        String lp = "";
        try(Connection cnn = JdbcUtils.getConn()){
            
            String sql = "SELECT licensePate FROM bus WHERE busID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, ID);
            
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                lp = rs.getString("licensePlate");
            }
            
            return lp;
        }catch(SQLException ex){
        
        }
        return null;
       
    }
}
