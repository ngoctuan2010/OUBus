/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.services;
import com.oubus.pojo.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author PC
 */
public class AccountServices {
    public Account getUser(String username, String password) throws SQLException{
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

}
