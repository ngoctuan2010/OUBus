/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.pojo;

import java.time.LocalDate;
import java.util.UUID;

/**
 *
 * @author PC
 */

public class Bill {
    
    public static enum statePayment{
        PAID,
        UNPAID,
        CANCEL
    }
    
    private String billID;
    private String customerID;
    private String employeeID;
    private int tripID;
    private int seat;
    private statePayment bookingState;
    private double totalDue;
    private LocalDate aqruiredDate;
    
    {
        setBillID(UUID.randomUUID().toString());
    }
    
    public Bill(String customerID, String employeeID, int tripID, int seat, statePayment state, double totalPrice){
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.tripID = tripID;
        this.seat = seat;
        this.bookingState = state;
        this.totalDue = totalPrice;
        this.aqruiredDate = LocalDate.now();
    }
    
    public Bill(String receiptID,String customerID, String employeeID, int tripID, int seat, statePayment state, double totalPrice){
        this.billID = receiptID;
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.tripID = tripID;
        this.seat = seat;
        this.bookingState = state;
        this.totalDue = totalPrice;
    }
    
    public Bill(){}

    /**
     * @return the billID
     */
    public String getBillID() {
        return billID;
    }

    /**
     * @param billID the billID to set
     */
    public void setBillID(String billID) {
        this.billID = billID;
    }

    /**
     * @return the customerID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     * @return the employeeID
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID the employeeID to set
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * @return the tripID
     */
    public int getTripID() {
        return tripID;
    }

    /**
     * @param tripID the tripID to set
     */
    public void setTripID(int tripID) {
        this.tripID = tripID;
    }
    /**
     * @return the bookingState
     */
    public statePayment getBookingState() {
        return bookingState;
    }

    /**
     * @param bookingState the bookingState to set
     */
    public void setBookingState(statePayment bookingState) {
        this.bookingState = bookingState;
    }

    /**
     * @return the totalDue
     */
    public double getTotalDue() {
        return totalDue;
    }

    /**
     * @param totalDue the totalDue to set
     */
    public void setTotalDue(double totalDue) {
        this.totalDue = totalDue;
    }

    /**
     * @return the seat
     */
    public int getSeat() {
        return seat;
    }

    /**
     * @param seat the seat to set
     */
    public void setSeat(int seat) {
        this.seat = seat;
    }

    /**
     * @return the aqruiredDate
     */
    public LocalDate getAqruiredDate() {
        return aqruiredDate;
    }

    /**
     * @param aqruiredDate the aqruiredDate to set
     */
    public void setAqruiredDate(LocalDate aqruiredDate) {
        this.aqruiredDate = aqruiredDate;
    }
    
    
}
