/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.services;

import com.oubus.pojo.Bus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
<<<<<<< HEAD
import javafx.scene.control.TableColumn;
=======
import com.oubus.pojo.Bus;
>>>>>>> 378ddbd3fef0d0c43d01e5e2f20d460c73e01f60

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
    
     public static Bus getBusbyID(int ID) throws SQLException{
        Bus b = new Bus();
        try(Connection cnn = JdbcUtils.getConn()){
            
            String sql = "SELECT * FROM bus WHERE busID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, ID);
            
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                b.setBusID(rs.getInt("busID"));
                b.setVehicleName(rs.getString("vehicleName"));
                b.setManufacturer(rs.getString("manufacturer"));
                b.setLicensePlate(rs.getString("licensePlate"));
                b.setTotalSeat(rs.getInt("totalSeat"));
                b.setBusType(rs.getString("busType"));
            }  
            return b;
        }

    }
   public List<Bus> getBusName() throws SQLException {
        List<Bus> bus = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            //Thuc thi truy van
            Statement stm = conn.createStatement();
            // Truy van du lieu --> select
            ResultSet rs = stm.executeQuery("SELECT * FROM bus");
            while (rs.next()) {
                int id = rs.getInt("busID");
                String verName = rs.getString("vehicleName");
                String manufac = rs.getString("manufacturer");
                String licenPla = rs.getString("licensePlate");
                int toSeat = rs.getInt("totalSeat");
                String bustype =rs.getString("busType");
                bus.add(new Bus(id,verName,manufac,licenPla,toSeat,bustype));
            }
        }
        
        return bus;
}
   
   
}
