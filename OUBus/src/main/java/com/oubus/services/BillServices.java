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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class BillServices {

    public List<Bill> getBill() throws SQLException {
        /*Create List Bill*/
        List<Bill> bills = new ArrayList<>();
        EmployeeServices es = new EmployeeServices();
        TripServices ts = new TripServices();
        /* Connetion to SQL Database*/
        try (Connection cnn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM bill";

            PreparedStatement stm = cnn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                String billID = rs.getString("billID");
                Customer cus = CustomerServices.getCustomerByID(rs.getString("customerID"));
                Employee emp = es.getEmployeeByID(rs.getString("employeeID"));
                Trip trip = ts.getTripByID(rs.getInt("tripID"));
                int seat = rs.getInt("seatNo");
                Bill.statePayment state = Bill.statePayment.values()[rs.getInt("state")];
                Double totalDue = rs.getDouble("totalDue");
                String aquiredDate = rs.getString("aquiredDate");
                Bill bill = new Bill(billID, cus, emp, trip, seat, state, totalDue, aquiredDate);
                bills.add(bill);
            }
            return bills;
        }
    }

    public boolean addBill(Bill bill) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {
            cnn.setAutoCommit(false);
            String sql = "INSERT INTO Bill(billID, customerID, employeeID, tripID, seatNo, state, totalDue, aquiredDate) VALUE(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setString(1, bill.getBillID());
            stm.setString(2, bill.getCustomer().getCustomerID());
            stm.setString(3, bill.getEmployee().getEmployeeID());
            stm.setInt(4, bill.getTrip().getTripID());
            stm.setInt(5, bill.getSeat());
            stm.setInt(6, bill.getBookingState().ordinal());
            stm.setInt(7, bill.getTrip().getPrice());
            stm.setString(8, bill.getAquiredDate());

            stm.executeUpdate();

            try {
                cnn.commit();
                return true;
            } catch (SQLException ex) {
                return false;
            }
        }
    }

    public boolean updateBill(Bill bill) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {
            cnn.setAutoCommit(false);
            String sql = "UPDATE Bill set customerID = ?, employeeID = ?, tripID = ?, seatNo = ?, state = ?, totalDue = ?, aquiredDate = ? WHERE billID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);

            stm.setString(1, bill.getCustomer().getCustomerID());
            stm.setString(2, bill.getEmployee().getEmployeeID());
            stm.setInt(3, bill.getTrip().getTripID());
            stm.setInt(4, bill.getSeat());
            stm.setInt(5, bill.getBookingState().ordinal());
            stm.setInt(6, bill.getTrip().getPrice());
            stm.setString(7, bill.getAquiredDate());
            stm.setString(8, bill.getBillID());

            stm.executeUpdate();

            try {
                cnn.commit();
                return true;
            } catch (SQLException ex) {
                return false;
            }
        }
    }

    public boolean deleteBill(String id) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {
            cnn.setAutoCommit(false);
            String sql = "DELETE Bill where BillID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);

            stm.setString(1, id);

            stm.executeUpdate();

            try {
                cnn.commit();
                return true;
            } catch (SQLException ex) {
                return false;
            }
        }
    }

    public static List<Bill> searchBillByCus(Customer cust) throws SQLException {
        List<Bill> bills = new ArrayList<>();
        EmployeeServices es = new EmployeeServices();
        TripServices ts = new TripServices();

        try (Connection cnn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM bill WHERE customerID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setString(1, cust.getCustomerID());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                String billID = rs.getString("billID");
                Customer cus = CustomerServices.getCustomerByID(rs.getString("customerID"));
                Employee emp = es.getEmployeeByID(rs.getString("employeeID"));
                Trip trip = ts.getTripByID(rs.getInt("tripID"));
                int seat = rs.getInt("seatNo");
                Bill.statePayment state = Bill.statePayment.values()[rs.getInt("state")];
                Double totalDue = rs.getDouble("totalDue");
                String aquiredDate = rs.getString("aquiredDate");

                Bill bill = new Bill(billID, cus, emp, trip, seat, state, totalDue, aquiredDate);
                bills.add(bill);
            }
            return bills;
        }
    }

    public static boolean checkSeatUnique(Bill bill) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {

            String sql = "SELECT * FROM bill WHERE tripID = ? and seatNo = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setInt(1, bill.getTrip().getTripID());
            stm.setInt(2, bill.getSeat());

            ResultSet rs = stm.executeQuery();
            return rs.next();
        }
    }

    public static boolean checkSeatBill(int seat, String tripID) throws SQLException {
        Bill b = new Bill();
        try (Connection cnn = JdbcUtils.getConn()) {
            String sql = "SELECT seatNo FROM bill WHERE tripID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setString(1, tripID);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (seat == rs.getInt("seatNo")) {
                    return true;
                }

            }

            return false;
        }

    }

    public static boolean checkExist(Bill bill) throws SQLException {
        try (Connection cnn = JdbcUtils.getConn()) {

            String sql = "SELECT * FROM bill WHERE billID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setString(1, bill.getBillID());

            ResultSet rs = stm.executeQuery();
            return rs.next();
        }
    }

    public static boolean checkOverSeat(Bill bill) throws SQLException {
        return (bill.getTrip().getBus().getTotalSeat()-bill.getSeat())>=0;
    }

    public List<Bill> searchBill(Customer cus, Trip tr) throws SQLException {
        List<Bill> bills = new ArrayList<>();
        EmployeeServices es = new EmployeeServices();
        TripServices ts = new TripServices();
        int i = 0;
        try (Connection cnn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM bill WHERE";
            if (cus != null) {
                sql += " customerID = ? AND";
                i++;
            }
            if (tr != null) {
                sql += " tripID = ? AND";
                i++;
            }
            sql = sql.substring(0, sql.length() - 3);
            PreparedStatement stm = cnn.prepareCall(sql);
            if (tr != null) {
                stm.setInt(i, tr.getTripID());
                i--;
            }
            if (cus != null) {
                stm.setString(i, cus.getCustomerID());
                i--;
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String billID = rs.getString("billID");
                Customer customer = CustomerServices.getCustomerByID(rs.getString("customerID"));
                Employee emp = es.getEmployeeByID(rs.getString("employeeID"));
                Trip trip = ts.getTripByID(rs.getInt("tripID"));
                int seat = rs.getInt("seatNo");
                Bill.statePayment state = Bill.statePayment.values()[rs.getInt("state")];
                Double totalDue = rs.getDouble("totalDue");
                String aquiredDate = rs.getString("aquiredDate");

                Bill bill = new Bill(billID, customer, emp, trip, seat, state, totalDue, aquiredDate);
                bills.add(bill);
            }
        }
        return bills;
    }
    
    public List<Bill> getInvalidBills() throws SQLException{
        List<Bill> bills = new ArrayList<>();
        EmployeeServices es = new EmployeeServices();
        TripServices ts = new TripServices();
        try(Connection conn = JdbcUtils.getConn()){
            String sql ="SELECT *FROM bill WHERE aquiredDate(time, INTERVAL 30 minute) < NOW() AND state = 1";
            PreparedStatement stm = conn.prepareCall(sql);
            ResultSet rs =stm.executeQuery();
            while (rs.next()) {
                String billID = rs.getString("billID");
                Customer customer = CustomerServices.getCustomerByID(rs.getString("customerID"));
                Employee emp = es.getEmployeeByID(rs.getString("employeeID"));
                Trip trip = ts.getTripByID(rs.getInt("tripID"));
                int seat = rs.getInt("seatNo");
                Bill.statePayment state = Bill.statePayment.values()[rs.getInt("state")];
                Double totalDue = rs.getDouble("totalDue");
                String aquiredDate = rs.getString("aquiredDate");
             
                Bill bill = new Bill(billID, customer, emp, trip, seat, state, totalDue, aquiredDate);
                bills.add(bill);
            }
            return bills;
        }
    }
    
    public  int getAmountTicketOfCustomer (String customerId) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()){
            conn.setAutoCommit(false);
            String sql =" SELECT COUNT(*) FROM ticket WHERE customerID =?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(0, customerId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
                return 0;
        }
    }
    
}
