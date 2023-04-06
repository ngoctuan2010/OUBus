/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.services;

import com.oubus.pojo.Bill;
import com.oubus.pojo.Customer;
import com.oubus.pojo.Employee;
import com.oubus.pojo.Trip;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class BillServices {
    public List<Bill> getBill() throws SQLException{
        /*Create List Bill*/
        List<Bill> bills = new ArrayList<>();
        /* Connetion to SQL Database*/
        try (Connection cnn = JdbcUtils.getConn())
        {
            String sql ="SELECT * FROM bill";
            
            PreparedStatement stm =cnn.prepareCall(sql);
            ResultSet rs =stm.executeQuery();
            
            while (rs.next()){
                String billID = rs.getString("billID");
                Customer cus = CustomerServices.getCustomerByID(rs.getString("customerID"));
                Employee emp = EmployeeServices.getEmployeeByID(rs.getString("employeeID"));
                Trip trip = TripServices.getTripByID(rs.getInt("tripID"));
                int seat=rs.getInt("seatNo");
                Bill.statePayment state = Bill.statePayment.values()[rs.getInt("state")];
                Double totalDue = rs.getDouble("totalDue");
                Date aquiredDate =rs.getDate("aquiredDate");
              
             
                Bill bill = new Bill(billID, cus, emp, trip, seat, state, totalDue, aquiredDate);
                bills.add(bill);
            }
            return bills;
        }
    }
   
    public boolean addTrip(Trip bill) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {
            cnn.setAutoCommit(false);
            String sql = "INSERT INTO Trip(busID, departure, TimeOfDeparture, DateOfDeparture, destination) VALUE(?, ?, ?, ?, ?)";
            PreparedStatement stm = cnn.prepareCall(sql);

            stm.setInt(1, bill.getBus().getBusID());
            stm.setInt(2, bill.getDeparture().getLocationID());
            stm.setString(3, bill.getTimeOfDeparture());
            stm.setString(4, bill.getDateOfDeparture());
            stm.setInt(5, bill.getDestination().getLocationID());

            stm.executeUpdate();

            try {
                cnn.commit();
                return true;
            } catch (SQLException ex) {
                return false;
            }
        }
}
