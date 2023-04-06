/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.services;
import com.oubus.pojo.Account;
import com.oubus.utils.MessageBox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
/**
 *
 * @author PC
 */
public class AccountServices {
    public Account getAccount(String username, String password) throws SQLException{
        try(Connection cnn = JdbcUtils.getConn()){
            Account ac;
            String sql = "SELECT * FROM accounts WHERE username = ? and password = ?";
            PreparedStatement stm = cnn.prepareCall(sql);
            stm.setString(1,username);
            stm.setString(2, password);
            
            ResultSet rs = stm.executeQuery();
            
            if(rs.next()){
                ac = new Account();
                ac.setAccountID(rs.getString("accountID"));
                ac.setEmployeeID(rs.getString("employeeID"));
                ac.setAccessLevel(Account.level.values()[rs.getInt("accessedLevel")]);
                ac.setUsername(username);
                ac.setPassword(password);
                return ac;
            }     
            return null;
        }
    }
    
    public boolean addAcount(Account ac) throws SQLException{
          try(Connection cnn = JdbcUtils.getConn()){
            cnn.setAutoCommit(false);
            String sql = "INSERT INTO account(accountID, employeeID,username, password,accessedLevel) VALUE(?, ?, ?, ?, EMPLOYEE)";
            PreparedStatement stm = cnn.prepareCall(sql);
            
            stm.setString(1, ac.getAccountID());
            stm.setString(2, ac.getEmployeeID());
            stm.setString(3, ac.getUsername());
            stm.setString(4, ac.getPassword());
            stm.setInt(5, ac.getAccessLevel().ordinal());
           
            stm.executeUpdate();
            
            try{
                cnn.commit();
                
                return true;
            }catch(SQLException ex){
              
                return false;
            }
          }
            
            
    }
}
