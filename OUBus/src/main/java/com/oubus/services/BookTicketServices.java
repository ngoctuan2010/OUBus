/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.services;

import com.oubus.pojo.Bill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 * @author pthin
 */
public class BookTicketServices {
    public List<Bill> getTrip() throws SQLException{
        List<Bill> trips = new ArrayList<>();
        try(Connection cnn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM bill";
            
            PreparedStatement stm = cnn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                String customerID = rs.getString("customerID");
                String employeeID = rs.getString("employeeID");                
                int tripID = rs.getInt("tripID");
                int seat = rs.getInt("seat");
                int bookingState = rs.getInt("bookingState");
                double totalPrice = rs.getDouble("totalPrice");
                
                Bill bill = new Bill(customerID, employeeID, tripID, seat, bookingState, totalPrice);
                trips.add(bill);
            }

            return trips;
        }
        
    }
}
