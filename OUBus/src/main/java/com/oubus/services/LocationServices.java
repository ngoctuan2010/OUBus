/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.services;

import java.util.List;
import com.oubus.pojo.Location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class LocationServices {
    public List<Location> getLocations() throws SQLException{
        List<Location> locations = new ArrayList<>();
        try(Connection cnn = JdbcUtils.getConn()){
            Statement stm = cnn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM location");
            while(rs.next()){
                Location l = new Location(rs.getInt("locationID"), rs.getString("name"));
                locations.add(l);
            }
            
        }
        return locations;
    } 
    
    public static Location getLocationById(int ID) throws SQLException{
        Location l = new Location();
        try(Connection cnn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM location WHERE locationID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, ID);
            
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                l.setLocationID(rs.getInt("locationID"));
                l.setName(rs.getString("name"));
                
            }
            
        }
        return l;
    }
}
