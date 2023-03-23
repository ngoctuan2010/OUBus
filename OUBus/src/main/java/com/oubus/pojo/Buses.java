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
public class Buses {

    private String busesID;
    private String busID;
    private String startedPlace;
    private String startedTime;
    private String endedPlace;

    {
        busesID = UUID.randomUUID().toString();
    }

    public Buses(String busID, String startedPlace, String startedTime, String endedPlace) {
        this.busID = busID;
        this.startedPlace = startedPlace;
        this.startedTime = startedTime;
        this.endedPlace = endedPlace;
    }

    public Buses(String busesID, String busID, String startedPlace, String startedTime, String endedPlace) {
        this.busesID = busesID;
        this.busID = busID;
        this.startedPlace = startedPlace;
        this.startedTime = startedTime;
        this.endedPlace = endedPlace;
    }
    
    public Buses() {}

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
     * @return the busID
     */
    public String getBusID() {
        return busID;
    }

    /**
     * @param busID the busID to set
     */
    public void setBusID(String busID) {
        this.busID = busID;
    }

    /**
     * @return the startedPlace
     */
    public String getStartedPlace() {
        return startedPlace;
    }

    /**
     * @param startedPlace the startedPlace to set
     */
    public void setStartedPlace(String startedPlace) {
        this.startedPlace = startedPlace;
    }

    /**
     * @return the startedTime
     */
    public String getStartedTime() {
        return startedTime;
    }

    /**
     * @param startedTime the startedTime to set
     */
    public void setStartedTime(String startedTime) {
        this.startedTime = startedTime;
    }

    /**
     * @return the endedPlace
     */
    public String getEndedPlace() {
        return endedPlace;
    }

    /**
     * @param endedPlace the endedPlace to set
     */
    public void setEndedPlace(String endedPlace) {
        this.endedPlace = endedPlace;
    }

}
