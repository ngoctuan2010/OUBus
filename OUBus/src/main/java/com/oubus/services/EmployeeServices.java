/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.services;

import com.oubus.pojo.Employee;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
                int sex = rs.getInt("sex");
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
    

}
