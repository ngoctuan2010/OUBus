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
public class Customer {
    private String customerID;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    
    {
        customerID = UUID.randomUUID().toString();
    }


    public Customer (String customerID, String name, String address, String email, String telephone)
    {
        this.customerID= customerID;
        this.name = name;
        this.address =address;
        this.email =email;
        this.phoneNumber =telephone;
    }

    public Customer ( String name, String address, String email, String telephone)
    {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = telephone;
    }

    public Customer(){}

    public String getCustomerID(){
        return this.customerID;
    }
    
    public void setCustomerID(String customerID){
        this.customerID = customerID;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name=name;
    }
 
    public String getAddress(){
        return this.address;
    }
    
    public void setAddress(String address){
        this.address= address;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setEmail(String email){
        this.email=email;
    }


    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public String toString(){
        return customerID;
    }
}


