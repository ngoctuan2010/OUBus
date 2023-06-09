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
            String sql = "INSERT INTO Trip(busID, departure, TimeOfDeparture, DateOfDeparture, destination, price, state) VALUE(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = cnn.prepareCall(sql);

            stm.setInt(1, trip.getBus().getBusID());
            stm.setInt(2, trip.getDeparture().getLocationID());
            stm.setString(3, trip.getTimeOfDeparture());
            stm.setString(4, trip.getDateOfDeparture());
            stm.setInt(5, trip.getDestination().getLocationID());
            stm.setInt(6, trip.getPrice());
            stm.setInt(7, 0);

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
            String sql = "SELECT tripID, busID, departure, TIME_FORMAT(TimeOfDeparture, '%H:%i') as time, DateOfDeparture, destination, price, state FROM trip";

            PreparedStatement stm = cnn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int tripID = rs.getInt("tripID");
                Bus bus = BusServices.getBusbyID(rs.getInt("busID"));
                Location departure = LocationServices.getLocationById(rs.getInt("departure"));
                String tOd = rs.getString("time");
                String dOd = rs.getString("DateOfDeparture");
                Location destination = LocationServices.getLocationById(rs.getInt("destination"));
                int price = rs.getInt("price");
                int state = rs.getInt("state");

                Trip trip = new Trip(tripID, bus, departure, tOd, dOd, destination, price, state);
                trips.add(trip);
            }

            return trips;
        }

    }

    public Trip getTripByID(int ID) throws SQLException {
        Trip t = new Trip();
        try (Connection cnn = JdbcUtils.getConn()) {

            String sql = "SELECT * FROM trip WHERE tripID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, ID);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
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

    public List<Trip> searchTrip(Bus bus, Location departure, Location destination, String tOd, String dOd) throws SQLException {
        List<Trip> trips = new ArrayList<>();
        try (Connection cnn = JdbcUtils.getConn()) {
            String sql = "SELECT tripID, busID, departure, TIME_FORMAT(TimeOfDeparture, '%H:%i') as time, DateOfDeparture, destination, price, state FROM trip WHERE";

//            stm.setInt(1, busID);
//            stm.setInt(2, departureID);
//            stm.setInt(3, destinationID);
//            stm.setString(4, tOd);
//            stm.setString(5, dOd);
            int i = 0;
            if (bus != null) {
                sql += " busID = ? and";
                i += 1;
            }
            if (departure != null) {
                sql += " departure = ? and";
                i += 1;
            }
            if (destination != null) {
                sql += " destination = ? and";
                i += 1;
            }
            if (tOd != null && !tOd.isEmpty()) {
                sql += " TIME_FORMAT(TimeOfDeparture, '%H:%i') = ? and";
                i += 1;
            }
            if (dOd != null && !dOd.isEmpty()) {
                sql += " DateOfDeparture = ? and";
                i += 1;
            }

            sql = sql.substring(0, sql.length() - 3);

            PreparedStatement stm = cnn.prepareCall(sql);
            if (dOd != null && !dOd.isEmpty()) {
                stm.setString(i, dOd);
                i -= 1;
            }
            if (tOd != null && !tOd.isEmpty()) {
                stm.setString(i, tOd);
                i -= 1;
            }
            if (destination != null) {
                stm.setInt(i, destination.getLocationID());
                i -= 1;
            }
            if (departure != null) {
                stm.setInt(i, departure.getLocationID());
                i -= 1;
            }
            if (bus != null) {
                stm.setInt(i, bus.getBusID());
            }

            stm.executeQuery();
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int tripID = rs.getInt("tripID");

                Bus _bus = BusServices.getBusbyID(rs.getInt("busID"));
                Location _ideparture = LocationServices.getLocationById(rs.getInt("departure"));
                String _time = rs.getString("time");
                String _date = rs.getString("DateOfDeparture");
                Location _destination = LocationServices.getLocationById(rs.getInt("destination"));
                int _price = rs.getInt("price");
                int _state = rs.getInt("state");
                Trip trip = new Trip(tripID, _bus, _ideparture, _time, _date, _destination, _price, _state);
                trips.add(trip);
            }

            return trips;
        }
    }

    public boolean updateTrip(Trip tr) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {
            cnn.setAutoCommit(false);
            String sql = "UPDATE trip SET busID = ?, departure = ?, TimeOfDeparture = ?, DateOfDeparture = ?, destination = ?, price = ? WHERE tripID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, tr.getBus().getBusID());
            stm.setInt(2, tr.getDeparture().getLocationID());
            stm.setString(3, tr.getTimeOfDeparture());
            stm.setString(4, tr.getDateOfDeparture());
            stm.setInt(5, tr.getDestination().getLocationID());
            stm.setInt(6, tr.getPrice());
            stm.setInt(7, tr.getTripID());

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

    public static boolean checkUnique(Trip trip) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {

            String sql = "SELECT * FROM trip WHERE busID = ? and departure = ? and destination = ? and TimeOfDeparture = ? and DateOfDeparture = ? and price = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, trip.getBus().getBusID());
            stm.setInt(2, trip.getDeparture().getLocationID());
            stm.setInt(3, trip.getDestination().getLocationID());
            stm.setString(4, trip.getTimeOfDeparture());
            stm.setString(5, trip.getDateOfDeparture());
            stm.setInt(6, trip.getPrice());

            ResultSet rs = stm.executeQuery();
            return rs.next();
        }
    }

    public int checkState(Trip tr) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {
            String sql = "SELECT billID, count(billID) as amount FROM bill WHERE tripID = ? GROUP BY billID";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, tr.getTripID());

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int total = rs.getInt("amount");
                if (total > tr.getBus().getTotalSeat()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

    public Trip getAfterTrip(Trip tr) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {
            String sql = "SELECT tripID, busID, departure, TIME_FORMAT(TimeOfDeparture, '%H:%i') as time, DateOfDeparture, destination, price, state FROM trip WHERE tripID != ? AND busID = ? AND DateOfDeparture = ? AND TIME_FORMAT(TimeOfDeparture, '%H:%i') > ? ORDER BY time LIMIT 1";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, tr.getTripID());
            stm.setInt(2, tr.getBus().getBusID());
            stm.setString(3, tr.getDateOfDeparture());
            stm.setString(4, tr.getTimeOfDeparture());

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int tripID = rs.getInt("tripID");
                Bus bus = BusServices.getBusbyID(rs.getInt("busID"));
                Location departure = LocationServices.getLocationById(rs.getInt("departure"));
                Location destination = LocationServices.getLocationById(rs.getInt("destination"));
                String tOd = rs.getString("time");
                String dOd = rs.getString("DateOfDeparture");
                int price = rs.getInt("price");
                int state = rs.getInt("state");

                Trip trip = new Trip(tripID, bus, departure, tOd, dOd, destination, price, state);
                return trip;
            }
            return null;
        }
    }

    public Trip getBeforeTrip(Trip tr) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {
            String sql = "SELECT tripID, busID, departure, TIME_FORMAT(TimeOfDeparture, '%H:%i') as time, DateOfDeparture, destination, price, state FROM trip WHERE tripID != ? AND busID = ? AND DateOfDeparture = ? and TIME_FORMAT(TimeOfDeparture, '%H:%i') < ? ORDER BY time DESC LIMIT 1";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, tr.getTripID());
            stm.setInt(2, tr.getBus().getBusID());
            stm.setString(3, tr.getDateOfDeparture());
            stm.setString(4, tr.getTimeOfDeparture());

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int tripID = rs.getInt("tripID");
                Bus bus = BusServices.getBusbyID(rs.getInt("busID"));
                Location departure = LocationServices.getLocationById(rs.getInt("departure"));
                Location destination = LocationServices.getLocationById(rs.getInt("destination"));
                String tOd = rs.getString("time");
                String dOd = rs.getString("DateOfDeparture");
                int price = rs.getInt("price");
                int state = rs.getInt("state");

                Trip trip = new Trip(tripID, bus, departure, tOd, dOd, destination, price, state);
                return trip;
            }
            return null;
        }
    }

    public Trip getFirstTripOfBus(int busID, String date) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {
            String sql = "SELECT tripID, busID, departure, TIME_FORMAT(TimeOfDeparture, '%H:%i') as time, DateOfDeparture, destination, price, state FROM trip WHERE busID = ? AND DateOfDeparture = ? ORDER BY time LIMIT 1";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, busID);
            stm.setString(2, date);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int tripID = rs.getInt("tripID");
                Bus bus = BusServices.getBusbyID(rs.getInt("busID"));
                Location departure = LocationServices.getLocationById(rs.getInt("departure"));
                Location destination = LocationServices.getLocationById(rs.getInt("destination"));
                String tOd = rs.getString("time");
                String dOd = rs.getString("DateOfDeparture");
                int price = rs.getInt("price");
                int state = rs.getInt("state");

                Trip trip = new Trip(tripID, bus, departure, tOd, dOd, destination, price, state);
                return trip;
            }
            return null;
        }
    }

    public Trip getLastTripOfBus(int busID, String date) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {
            String sql = "SELECT tripID, busID, departure, TIME_FORMAT(TimeOfDeparture, '%H:%i') as time, DateOfDeparture, destination, price, state FROM trip WHERE busID = ? AND DateOfDeparture = ? ORDER BY time DESC LIMIT 1";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, busID);
            stm.setString(2, date);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int tripID = rs.getInt("tripID");
                Bus bus = BusServices.getBusbyID(rs.getInt("busID"));
                Location departure = LocationServices.getLocationById(rs.getInt("departure"));
                Location destination = LocationServices.getLocationById(rs.getInt("destination"));
                String tOd = rs.getString("time");
                String dOd = rs.getString("DateOfDeparture");
                int price = rs.getInt("price");
                int state = rs.getInt("state");

                Trip trip = new Trip(tripID, bus, departure, tOd, dOd, destination, price, state);
                return trip;
            }
            return null;
        }
    }

    public List<Trip> getTripByBusID(int busID) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {
            List<Trip> trips = new ArrayList<>();
            String sql = "SELECT * FROM trip WHERE busID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, busID);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Trip t = new Trip();
                t.setTripID(rs.getInt("tripID"));
                t.setBus(BusServices.getBusbyID(rs.getInt("busID")));
                t.setDeparture(LocationServices.getLocationById(rs.getInt("departure")));
                t.setTimeOfDeparture(rs.getString("TimeOfDeparture"));
                t.setDateOfDeparture(rs.getString("DateOfDeparture"));
                t.setDestination(LocationServices.getLocationById(rs.getInt("destination")));

                trips.add(t);
            }
            return trips;
        }
    }
}
