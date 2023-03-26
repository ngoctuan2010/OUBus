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
public class Bus {
    private int busID;
    private String vehicleName;
    private String manufacturer;
    private String licensePlate;
    private int totalSeat;
    private String busType;
    

    
    public Bus(String vehicleName, String manufacturer, String licensePlate, int totalSeat, String busType){
        this.vehicleName = vehicleName;
        this.manufacturer = manufacturer;
        this.licensePlate = licensePlate;
        this.totalSeat = totalSeat;
        this.busType = busType;      
    }
    
        
    public Bus(int nusID, String vehicleName, String manufacturer, String licensePlate, int totalSeat, String busType){
        this.busID = busID;
        this.vehicleName = vehicleName;
        this.manufacturer = manufacturer;
        this.licensePlate = licensePlate;
        this.totalSeat = totalSeat;
        this.busType = busType;      
    }
    
    public Bus(){}

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
     * @return the vehicleName
     */
    public String getVehicleName() {
        return vehicleName;
    }

    /**
     * @param vehicleName the vehicleName to set
     */
    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    /**
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the licensePlate
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * @param licensePlate the licensePlate to set
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /**
     * @return the totalSeat
     */
    public int getTotalSeat() {
        return totalSeat;
    }

    /**
     * @param totalSeat the totalSeat to set
     */
    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }

    /**
     * @return the busType
     */
    public String getBusType() {
        return busType;
    }

    /**
     * @param busType the busType to set
     */
    public void setBusType(String busType) {
        this.busType = busType;
    }
    
    @Override
    public String toString(){
        return this.licensePlate;
    }
   
}
