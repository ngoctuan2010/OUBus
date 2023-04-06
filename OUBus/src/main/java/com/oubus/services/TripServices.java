/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.services;

import com.oubus.services.JdbcUtils;
import com.oubus.services.BusServices;
import com.oubus.pojo.Trip;
import com.oubus.pojo.Bus;
import com.oubus.pojo.Location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class TripServices {

    public boolean addTrip(Trip trip) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {
            cnn.setAutoCommit(false);
            String sql = "INSERT INTO Trip(busID, departure, TimeOfDeparture, DateOfDeparture, destination) VALUE(?, ?, ?, ?, ?)";
            PreparedStatement stm = cnn.prepareCall(sql);

            stm.setInt(1, trip.getBus().getBusID());
            stm.setInt(2, trip.getDeparture().getLocationID());
            stm.setString(3, trip.getTimeOfDeparture());
            stm.setString(4, trip.getDateOfDeparture());
            stm.setInt(5, trip.getDestination().getLocationID());

            stm.executeUpdate();

            try {
                cnn.commit();
                return true;
            } catch (SQLException ex) {
                return false;
            }
        }

    }

    public List<Trip> getTrip() throws SQLException {
        List<Trip> trips = new ArrayList<>();
        try (Connection cnn = JdbcUtils.getConn()) {
            String sql = "SELECT tripID, busID, departure, TIME_FORMAT(TimeOfDeparture, '%H:%i') as time, DateOfDeparture, destination FROM trip";

            PreparedStatement stm = cnn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int tripID = rs.getInt("tripID");


                Bus bus = BusServices.getBusbyID(rs.getInt("busID"));
                Location departure = LocationServices.getLocationById(rs.getInt("departure"));
                String tOd = rs.getString("time");
                String dOd = rs.getString("DateOfDeparture");
                Location destination = LocationServices.getLocationById(rs.getInt("destination"));

                Trip trip = new Trip(tripID, bus, departure, tOd, dOd, destination);
                trips.add(trip);
            }

            return trips;
        }

    }
    
    public static Trip getTripByID(int ID) throws SQLException{
        Trip t = new Trip();
        try(Connection cnn = JdbcUtils.getConn()){
            
            String sql = "SELECT * FROM trip WHERE tripID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, ID);
            
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                t.setTripID(rs.getInt("tripID"));
                t.setBus(BusServices.getBusbyID(rs.getInt("busID")));
                t.setDeparture(LocationServices.getLocationById(rs.getInt("departure")));
                t.setTimeOfDeparture(rs.getString("TimeOfDeparture"));
                t.setDateOfDeparture(rs.getString("DateOfDeparture"));
                t.setDestination(LocationServices.getLocationById(rs.getInt("destination")));
               
            }  
            return t;
        }
    }

   

    public boolean updateTrip(Trip tr) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {
            cnn.setAutoCommit(false);
            String sql = "UPDATE trip SET busID = ?, departure = ?, TimeOfDeparture = ?, DateOfDeparture = ?, destination = ? WHERE tripID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, tr.getBus().getBusID());
            stm.setInt(2, tr.getDeparture().getLocationID());
            stm.setString(3, tr.getTimeOfDeparture());
            stm.setString(4, tr.getDateOfDeparture());
            stm.setInt(5, tr.getDestination().getLocationID());
            stm.setInt(6, tr.getTripID());

            stm.executeUpdate();

            try {
                cnn.commit();
                return true;
            } catch (SQLException ex) {
                return false;
            }
        }
    }

    public boolean deleteTrip(int id) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {
            cnn.setAutoCommit(false);
            String sql = "DELETE FROM trip WHERE tripID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, id);
            stm.executeUpdate();

            try {
                cnn.commit();
                return true;
            } catch (SQLException ex) {
                return false;
            }
        }
    }
}

