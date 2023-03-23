/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.pojo;

import java.util.UUID;

/**
 *
 * @author PC
 */

public class Receipt {
    
    public static enum statePayment{
        PAID,
        UNPAID,
        CANCEL
    }
    
    private String receiptID;
    private String customerID;
    private String employeeID;
    private String busesID;
    private int seat;
    private statePayment state;
    private double totalPrice;
    
    {
        setReceiptID(UUID.randomUUID().toString());
    }
    
    public Receipt(String customerID, String employeeID, String busesID, int seat, statePayment state, double totalPrice){
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.busesID = busesID;
        this.seat = seat;
        this.state = state;
        this.totalPrice = totalPrice;
    }
    
    public Receipt(String receiptID,String customerID, String employeeID, String busesID, int seat, statePayment state, double totalPrice){
        this.receiptID = receiptID;
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.busesID = busesID;
        this.seat = seat;
        this.state = state;
        this.totalPrice = totalPrice;
    }
    
    public Receipt(){}

    /**
     * @return the receiptID
     */
    public String getReceiptID() {
        return receiptID;
    }

    /**
     * @param receiptID the receiptID to set
     */
    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
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
     * @return the busesID
     */
    public String getBusesID() {
        return busesID;
    }

    /**
     * @param busesID the busesID to set
     */
    public void setBusesID(String busesID) {
        this.busesID = busesID;
    }

    /**
     * @return the seat
     */
    public int getSeet() {
        return seat;
    }

    /**
     * @param seet the seat to set
     */
    public void setSeet(int seet) {
        this.seat = seet;
    }

    /**
     * @return the state
     */
    public statePayment getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(statePayment state) {
        this.state = state;
    }

    /**
     * @return the totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    
}
