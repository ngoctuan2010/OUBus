/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.pojo;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

/**
 *
 * @author PC
 */
public class Trip {

    private int tripID;
    private Bus bus;
    private int departure;
    private Date TimeOfDeparture;
    private Date DateOfDeparture; 
    private int destination;


    public Trip(Bus bus, Location departure, String TimeOfDeparture, String  DateOfDeparture, Location destination) {
        this.bus = bus;
        this.departure = departure;
        this.TimeOfDeparture = TimeOfDeparture;
        this.DateOfDeparture = DateOfDeparture;
        this.destination = destination;
    }

    public Trip(int tripID, Bus bus, Location departure, String TimeOfDeparture, String  DateOfDeparture, Location destination) {
        this.tripID = tripID;
        this.bus = bus;
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
     * @return the bus
     */
    public Bus getBus() {
        return bus;
    }

    /**
     * @param bus
     */
    public void setBus(Bus bus) {
        this.bus = bus;
    }

    /**
     * @return the departure
     */
    public Location getDeparture() {
        return departure;
    }

    /**
     * @param departure the departure to set
     */
    public void setDeparture(Location departure) {
        this.departure = departure;
    }

    /**
     * @return the TimeOfDeparture
     */
    public String getTimeOfDeparture() {
        return TimeOfDeparture;
    }

    /**
     * @param TimeOfDeparture the TimeOfDeparture to set
     */
    public void setTimeOfDeparture(String TimeOfDeparture) {
        this.TimeOfDeparture = TimeOfDeparture;
    }

    /**
     * @return the destination
     */
    public Location getDestination() {
        return destination;
    }

    
    /**
     * @param destination the destination to set
     */
    public void setDestination(Location destination) {
        this.destination = destination;
    }

    /**
     * @return the DateOfDeparture
     */
    public String getDateOfDeparture() {
        return DateOfDeparture;
    }

    /**
     * @param DateOfDeparture the DateOfDeparture to set
     */
    public void setDateOfDeparture(String DateOfDeparture) {
        this.DateOfDeparture = DateOfDeparture;
    }

    
}
