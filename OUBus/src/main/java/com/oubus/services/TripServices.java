/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.services;
import com.oubus.pojo.Trip;
import com.oubus.utils.MessageBox;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;


/**
 *
 * @author PC
 */
public class TripServices {
    
    public boolean addTrip(Trip trip) throws SQLException{
        try(Connection cnn = JdbcUtils.getConn()){
            cnn.setAutoCommit(false);
            String sql = "INSERT INTO Trip(busID, departure, TimeOfDeparture, DateOfDeparture, destination) VALUE(?, ?, ?, ?, ?)";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, trip.getBusID());
            stm.setInt(2, trip.getDeparture());
            stm.setDate(3, trip.getTimeOfDeparture());
            stm.setDate(4, trip.getDateOfDeparture());
            stm.setInt(5, trip.getDestination());
            
            try{
                cnn.commit();
                MessageBox.getBox("Success", "Add trip completely", Alert.AlertType.CONFIRMATION);
                return true;
            }catch(SQLException ex){
                MessageBox.getBox("Fail", "Add trip failure", Alert.AlertType.WARNING);
                return false;
            }}
            
            
    }
    
    public List<Trip> getTrip() throws SQLException{
        List<Trip> trips = new ArrayList<>();
        try(Connection cnn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM trip";
            
            PreparedStatement stm = cnn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                int tripID = rs.getInt("tripID");
                int busID = rs.getInt("busID");                
                int departure = rs.getInt("departure");
                Date tOd = rs.getDate("TimeOfDeparture");
                Date dOd = rs.getDate("DateOfDeparture");
                int destination = rs.getInt("destination");
                
                Trip trip = new Trip(tripID, busID, departure, tOd, dOd, destination);
                trips.add(trip);
            }

            return trips;
        }
        
    }

}