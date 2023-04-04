/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.pojo;

import java.sql.Date;
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
    private Customer customerID;
    private Employee employeeID;
    private Trip tripID;
    private int seat;
    private statePayment bookingState;
    private double totalDue;
    private Date aquiredDate;
    
    {
        setBillID(UUID.randomUUID().toString());
    }
    
    public Bill(Customer customerID, Employee employeeID, Trip tripID, int seat, statePayment state, double totalPrice, Date aquireDate){
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.tripID = tripID;
        this.seat = seat;
        this.bookingState = state;
        this.totalDue = totalPrice;
        this.aquiredDate=aquireDate;
    }
    
    public Bill(String billID,Customer customerID, Employee employeeID, Trip tripID, int seat, statePayment state, double totalPrice, Date aquireDate){
        this.billID = billID;
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.tripID = tripID;
        this.seat = seat;
        this.bookingState = state;
        this.totalDue = totalPrice;
        this.aquiredDate =aquireDate;
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
    public Customer getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(Customer customerID) {
        this.customerID = customerID;
    }

    /**
     * @return the employeeID
     */
    public Employee getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID the employeeID to set
     */
    public void setEmployeeID(Employee employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * @return the tripID
     */
    public Trip getTripID() {
        return tripID;
    }

    /**
     * @param tripID the tripID to set
     */
    public void setTripID(Trip tripID) {
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
     * @return the aquiredDate
     */
    public Date getAquiredDate() {
        return aquiredDate;
    }

    /**
     * @param aquiredDate the aquiredDate to set
     */
    public void setAquiredDate(Date aquiredDate) {
        this.aquiredDate = aquiredDate;
    }
    
    
}
