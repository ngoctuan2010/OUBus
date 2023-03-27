/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.services;

import com.oubus.pojo.Location;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bthta
 */
public class LocationService {
     public List<Location> getLocationName() throws SQLException {
        List<Location> loca = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            //Thuc thi truy van
            Statement stm = conn.createStatement();
            // Truy van du lieu --> select
            ResultSet rs = stm.executeQuery("SELECT * FROM location");
            while (rs.next()) {
                int id = rs.getInt("locationID");
                String name = rs.getString("name");
                loca.add(new Location(id, name));
            }
        }
        
        return loca;
    }
}
