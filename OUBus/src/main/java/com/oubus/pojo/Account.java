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
public class Account {
    public static enum level {
        ADMIN,
        EMPLOYEE
    }
        
    private String accountID;
    private String employeeID;
    private String username;
    private String password;
    private level accessLevel;
    
    {
        setAccountID(UUID.randomUUID().toString());
    }
    
    public Account(String employeeID, String username, String password, level accessLevel) {
        this.employeeID = employeeID;
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
    }
    
    public Account(String accountID,String employeeID,String username,String password, level accessLevel){
        this.accountID = accountID;
        this.employeeID = employeeID;
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
    }
    
    public Account() {}

    /**
     * @return the accountID
     */
    public String getAccountID() {
        return accountID;
    }

    /**
     * @param accountID the accountID to set
     */
    public void setAccountID(String accountID) {
        this.accountID = accountID;
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the accessLevel
     */
    public level getAccessLevel() {
        return accessLevel;
    }

    /**
     * @param accessLevel the accessLevel to set
     */
    public void setAccessLevel(level accessLevel) {
        this.accessLevel = accessLevel;
    }
    
    
}
