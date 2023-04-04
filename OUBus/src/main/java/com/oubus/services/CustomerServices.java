/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.services;

import com.oubus.pojo.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class CustomerServices {
    
    public static Customer getCustomerByID(String ID) throws SQLException{
        Customer c = new Customer();
        try(Connection cnn = JdbcUtils.getConn()){
            
            String sql = "SELECT * FROM customer WHERE customerID like concat('%',?,'%')";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setString(1, ID);
            
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                c.setCustomerID(rs.getString("customerID"));
                c.setName(rs.getString("name"));
                c.setAddress(rs.getString("address"));
                c.setEmail(rs.getString("Email"));
                c.setPhoneNumber(rs.getString("phoneNumber"));
              
            }  
            return c;
        }
    }
    
    public List<Customer> getCustomer() throws SQLException{
            List<Customer> customers = new ArrayList<>();
            try (Connection cnn = JdbcUtils.getConn()) { 
               String sql ="SELECT * FROM customer";
                
               PreparedStatement stm = cnn.prepareCall(sql);
               ResultSet rs = stm.executeQuery();
               
               while(rs.next()){
                String customerID = rs.getString("customerID");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone =rs.getString("phoneNumber");
                
                Customer cus = new Customer(customerID, name, address, email,phone);
                customers.add(cus);
                }
                return customers;
            }         
           
    }
    
     public boolean addCustomer(Customer customer) throws SQLException{
        try(Connection cnn = JdbcUtils.getConn()){
            cnn.setAutoCommit(false);
            String sql = "INSERT INTO customer(customerID, name, address, email, phoneNumber) VALUE(?, ?, ?, ?, ?)";
            PreparedStatement stm = cnn.prepareCall(sql);
            
            stm.setString(1, customer.getCustomerID());
            stm.setString(2, customer.getName());
            stm.setString(3, customer.getAddress());
            stm.setString(4, customer.getEmail()); 
            stm.setString(5, customer.getPhoneNumber());
            stm.executeUpdate();
            
            try{
                cnn.commit();
//                MessageBox.getBox("Success", "Add customer completely", Alert.AlertType.CONFIRMATION).show();
                return true;
            }catch(SQLException ex){
//                MessageBox.getBox("Fail", "Add customer failure", Alert.AlertType.WARNING).show();
                return false;
            }}
            
            
    }
}
