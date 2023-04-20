/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javafx.scene.control.TableColumn;
import com.oubus.pojo.Bus;
import com.oubus.pojo.Trip;
import com.oubus.services.RuleSetServices;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author PC
 */
public class BusServices {

    public static String getLicensePlateByID(int ID) {
        String lp = "";
        try (Connection cnn = JdbcUtils.getConn()) {

            String sql = "SELECT licensePate FROM bus WHERE busID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, ID);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                lp = rs.getString("licensePlate");
            }

            return lp;
        } catch (SQLException ex) {

        }
        return null;
    }

    public static Bus getBusbyID(int ID) throws SQLException {
        Bus b = new Bus();
        try (Connection cnn = JdbcUtils.getConn()) {

            String sql = "SELECT * FROM bus WHERE busID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, ID);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
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

    public Bus getBusByLicensePlate(String plate) throws SQLException {

        try (Connection cnn = JdbcUtils.getConn()) {
            Bus b = new Bus();
            String sql = "SELECT * FROM bus WHERE licensePlate like ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setString(1, plate);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                b.setBusID(rs.getInt("busID"));
                b.setVehicleName(rs.getString("vehicleName"));
                b.setManufacturer(rs.getString("manufacturer"));
                b.setLicensePlate(rs.getString("licensePlate"));
                b.setTotalSeat(rs.getInt("totalSeat"));
                b.setBusType(rs.getString("busType"));

                return b;
            }
            return null;
        }

    }

    public List<Bus> getBuses() throws SQLException {
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
                String bustype = rs.getString("busType");
                bus.add(new Bus(id, verName, manufac, licenPla, toSeat, bustype));
            }
        }
        return bus;
    }

    public boolean addBus(Bus b) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {
            cnn.setAutoCommit(false);
            String sql = "INSERT INTO Bus(vehicleName, manufacturer, licensePlate, totalSeat, busType) VALUE(?, ?, ?, ?, ?)";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setString(1, b.getVehicleName());
            stm.setString(2, b.getManufacturer());
            stm.setString(3, b.getLicensePlate());
            stm.setInt(4, b.getTotalSeat());
            stm.setString(5, b.getBusType());

            stm.executeUpdate();

            try {
                cnn.commit();
                return true;
            } catch (SQLException ex) {
                return false;
            }
        }
    }

    public boolean updateBus(Bus b) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {
            cnn.setAutoCommit(false);
            String sql = "UPDATE bus SET vehicleName = ?, manufacturer = ?, licensePlate = ?, totalSeat = ?, busType = ? WHERE busID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setString(1, b.getVehicleName());
            stm.setString(2, b.getManufacturer());
            stm.setString(3, b.getLicensePlate());
            stm.setInt(4, b.getTotalSeat());
            stm.setString(5, b.getBusType());
            stm.setInt(6, b.getBusID());
            stm.executeUpdate();

            try {
                cnn.commit();
                return true;
            } catch (SQLException ex) {
                return false;
            }
        }
    }

    public boolean deleteBus(int busID) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {
            cnn.setAutoCommit(false);
            String sql = "DELETE FROM bus WHERE busID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, busID);
            stm.executeUpdate();

            try {
                cnn.commit();
                return true;
            } catch (SQLException ex) {
                return false;
            }
        }

    }

    public boolean checkBusWorking(int busID) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {
            TripServices ts = new TripServices();
            List<Trip> trips = ts.getTripByBusID(busID);

            java.util.Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String aDate = dateFormat.format(date);

            for (Trip tr : trips) {
                String tDate = tr.getDateOfDeparture() + " " + tr.getTimeOfDeparture() + ":00";

                if (RuleSetServices.CheckTime(RuleSetServices.timeCalculator(aDate, tDate), 0)) {
                    return true;
                }
            }
            
            return false;

        }
    }

}
