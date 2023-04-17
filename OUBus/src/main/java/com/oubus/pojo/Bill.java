/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.pojo;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 *
 * @author PC
 */
public class Bill {

    public static enum statePayment {
        PAID,
        BOOKED,
        CANCELLED
    }

    private String billID;
    private Customer customer;
    private Employee employee;
    private Trip trip;
    private int seat;
    private statePayment bookingState;
    private double totalDue;
    private String aquiredDate;

    {
        billID = UUID.randomUUID().toString();
    }

    public Bill(Customer customerID, Employee employeeID, Trip tripID, int seat, statePayment state, double totalPrice, String aquireDate) {
        this.customer = customerID;
        this.employee = employeeID;
        this.trip = tripID;
        this.seat = seat;
        this.bookingState = state;
        this.totalDue = totalPrice;
        this.aquiredDate = aquireDate;
    }

    public Bill(String billID, Customer customerID, Employee employeeID, Trip tripID, int seat, statePayment state, double totalPrice, String aquireDate) {
        this.billID = billID;
        this.customer = customerID;
        this.employee = employeeID;
        this.trip = tripID;
        this.seat = seat;
        this.bookingState = state;
        this.totalDue = totalPrice;
        this.aquiredDate = aquireDate;
    }

    public Bill() {
    }

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
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * @return the trip
     */
    public Trip getTrip() {
        return trip;
    }

    /**
     * @param trip the trip to set
     */
    public void setTrip(Trip trip) {
        this.trip = trip;
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
    public String getAquiredDate() {
        return aquiredDate;
    }

    /**
     * @param aquiredDate the aquiredDate to set
     */
    public void setAquiredDate(String aquiredDate) {
        this.aquiredDate = aquiredDate;
    }

}
