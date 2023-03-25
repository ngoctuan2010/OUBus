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
public class Trip {

    private int tripID;
    private int busID;
    private int departure;
    private Date TimeOfDeparture;
    private Date  DateOfDeparture; 
    private int destination;

    public Trip(int busID, int departure, Date TimeOfDeparture, Date  DateOfDeparture, int destination) {
        this.busID = busID;
        this.departure = departure;
        this.TimeOfDeparture = TimeOfDeparture;
        this.DateOfDeparture = DateOfDeparture;
        this.destination = destination;
    }

    public Trip(int tripID, int busID, int departure, Date TimeOfDeparture, Date  DateOfDeparture, int destination) {
        this.tripID = tripID;
        this.busID = busID;
        this.departure = departure;
        this.TimeOfDeparture = TimeOfDeparture;
        this.DateOfDeparture = DateOfDeparture;
        this.destination = destination;
    }
    
    public Trip() {}

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
     * @return the busID
     */
    public int getBusID() {
        return busID;
    }

    /**
     * @param busID the busID to set
     */
    public void setBusID(int busID) {
        this.busID = busID;
    }

    /**
     * @return the departure
     */
    public int getDeparture() {
        return departure;
    }

    /**
     * @param departure the departure to set
     */
    public void setDeparture(int departure) {
        this.departure = departure;
    }

    /**
     * @return the TimeOfDeparture
     */
    public Date getTimeOfDeparture() {
        return TimeOfDeparture;
    }

    /**
     * @param TimeOfDeparture the TimeOfDeparture to set
     */
    public void setTimeOfDeparture(Date TimeOfDeparture) {
        this.TimeOfDeparture = TimeOfDeparture;
    }

    /**
     * @return the destination
     */
    public int getDestination() {
        return destination;
    }

    
    /**
     * @param destination the destination to set
     */
    public void setDestination(int destination) {
        this.destination = destination;
    }

    /**
     * @return the DateOfDeparture
     */
    public Date getDateOfDeparture() {
        return DateOfDeparture;
    }

    /**
     * @param DateOfDeparture the DateOfDeparture to set
     */
    public void setDateOfDeparture(Date DateOfDeparture) {
        this.DateOfDeparture = DateOfDeparture;
    }

    
}
