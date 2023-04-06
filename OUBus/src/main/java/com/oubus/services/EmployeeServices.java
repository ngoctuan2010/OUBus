/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.services;

import com.oubus.pojo.Employee;
import com.oubus.utils.MessageBox;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import javafx.scene.control.Alert;


/**
 *
 * @author PC
 */
public class EmployeeServices {

    public List<Employee> getEmployees() throws SQLException{
        List<Employee> employees = new ArrayList<>();
        try(Connection cnn = JdbcUtils.getConn()){
            Statement stm = cnn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM employee");
            while(rs.next()){
                String id = rs.getString("EmployeeID");
                String name = rs.getString("name");
                boolean sex = rs.getBoolean("sex");
                String dOb = rs.getString("DateOfBirth");
                String nationlity = rs.getString("nationality");
                String nationalID = rs.getString("nationalID");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phoneNumber");
                String position = rs.getString("Position");
                
                Employee emp = new Employee(id, name, sex, dOb, nationlity, nationalID, address, email,phone, position);
                employees.add(emp);
            }
        }
          return employees;
    }
    
    public  Employee getEmployeeByID(String ID) throws SQLException{
        Employee e = new Employee();
        try(Connection cnn = JdbcUtils.getConn()){
            
            String sql = "SELECT * FROM employee WHERE employeeID = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setString(1, ID);
            
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                e.setEmployeeID(rs.getString("employeeID"));
                e.setName(rs.getString("name"));
                e.setSex(rs.getBoolean("sex"));
                e.setDateOfBirth(rs.getString("DateOfBirth"));
                e.setNationality(rs.getString("nationality"));
                e.setNationalID(rs.getString("nationalID"));
                e.setAddress(rs.getString("address"));
                e.setEmail(rs.getString("email"));
                e.setTelephone(rs.getString("phoneNumber"));
                e.setPosition(rs.getString("position"));
              
            }  
            return e;
        }

    }
    
    public boolean addEmployee(Employee emp) throws SQLException{
           
          try(Connection cnn = JdbcUtils.getConn()){
            cnn.setAutoCommit(false);
            String sql = "INSERT INTO employee(employeeID, name, sex, DateOfBirth, nationality,nationalID,address,email,phoneNumber,position) VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = cnn.prepareCall(sql);
            
            stm.setString(1, emp.getEmployeeID());
            stm.setString(2, emp.getName());
            stm.setBoolean(3, emp.getSex());
            stm.setString(4, emp.getDateOfBirth()); 
            stm.setString(5, emp.getNationality());
            stm.setString(6, emp.getNationalID());
            stm.setString(7, emp.getAddress());
            stm.setString(8, emp.getEmail());
            stm.setString(9, emp.getTelephone());
            stm.setString(10, emp.getPosition());
            
            stm.executeUpdate();
            
            try{
                cnn.commit();
                MessageBox.getBox("Success", "Add employee completely", Alert.AlertType.CONFIRMATION).show();
                return true;
            }catch(SQLException ex){
                MessageBox.getBox("Fail", "Add employee failure", Alert.AlertType.WARNING).show();
                return false;
            }
          }
            
            
    }
}
