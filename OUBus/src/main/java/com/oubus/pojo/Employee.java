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
public class Employee {

    private String employeeID;
    private String name;
    private int sex;
    private String DateOfBirth;
    private String nationality;
    private String nationalID;
    private String address;
    private String email;
    private String telephone;
    private String position;

    {
        employeeID = UUID.randomUUID().toString();
    }

    public Employee(String name, int sex, String dOb, String nationality, String nationalID, String address, String email, String telephone, String position) {
        this.name = name;
        this.address = address;
        this.sex = sex;
        this.DateOfBirth = dOb;
        this.nationality = nationality;
        this.nationalID = nationalID;
        this.address = address;
        this.email = email;
        this.telephone = telephone;
        this.position = position;
    }

    public Employee(String id, String name, int sex, String dOb, String nationality, String nationalID, String address, String email, String telephone, String position) {
        this.employeeID = id;
        this.name = name;
        this.address = address;
        this.sex = sex;
        this.DateOfBirth = dOb;
        this.nationality = nationality;
        this.nationalID = nationalID;
        this.address = address;
        this.email = email;
        this.telephone = telephone;
        this.position = position;
    }

    public Employee() {
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the sex
     */
    public int getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(int sex) {
        this.sex = sex;
    }

    /**
     * @return the DateOfBirth
     */
    public String getDateOfBirth() {
        return DateOfBirth;
    }

    /**
     * @param DateOfBirth the DateOfBirth to set
     */
    public void setDateOfBirth(String DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality the nationality to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @return the nationalID
     */
    public String getNationalID() {
        return nationalID;
    }

    /**
     * @param nationalID the nationalID to set
     */
    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    
    
}
