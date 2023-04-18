
import com.oubus.pojo.Customer;
import com.oubus.services.BusServices;
import com.oubus.services.JdbcUtils;
import com.oubus.services.LocationServices;
import com.oubus.services.TripServices;
import com.oubus.services.CustomerServices;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.oubus.utils.MessageBox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bthta
 */
public class CustomerTester {
    private static Connection cnn;
    private static TripServices ts;
    private static BusServices bs;
    private static LocationServices ls;
    private static CustomerServices cs;
//    
    @BeforeAll
    public static void beforeAll(){
        try {
            cnn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(TripTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ts = new TripServices();
        bs = new BusServices();
        ls = new LocationServices();
        cs = new CustomerServices();
    }
    
    @AfterAll
    public static void afterAll(){
        if(cnn != null)
            try {
                cnn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TripTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    @Test
//    public void testGetCustomerByID() throws SQLException{
//        String ID = "1";
//        String name ="Thanh Dat";
//        String address ="371 NK";
//        String email = "thanhdat@gmail.com";
//        String phone ="012345678";
//        Customer expected = new Customer(ID, name, address, email, phone);
//        
//        Customer actual = CustomerServices.getCustomerByID(ID);
//        Assertions.assertEquals(expected, actual);
//    }  
    
    @Test
    public void testAddCustomer() throws SQLException {
    
        String name ="Thanh Dat";
        String address ="371 NK";
        String email = "thanhdat@gmail.com";
        String phone ="012345678";
        
        Customer c = new Customer(name, address, email, phone);
       
        boolean actual = cs.addCustomer(c);
        Assertions.assertTrue(actual);
        
    }
    
    @Test
    public void testDeleteCustomer() throws SQLException {
        String id = "3e11f00b-ffa1-4fb8-bc8b-8a6f22a2bb1d";
        boolean actual = cs.deleteCustomer(id);
        Assertions.assertTrue(actual);
    }
    
    @Test
    public void testUpdateCustomer() throws SQLException{
        String id = "1";
        String name ="Thanh Dat";
        String address ="371 NK";
        String email = "thanhdat@gmail.com";
        String phone ="012345678";
        
        Customer c = new Customer(id, name, address, email, phone);
       
        boolean actual = cs.updateCustomer(c);
        Assertions.assertTrue(actual);
    }
    
    @Test
    public void testCheckUnique() throws SQLException{
      String id = "3e11f00b-ffa1-4fb8-bc8b-8a6f22a2bb1d";
      String name ="Thanh Dat";
      String address ="371 NK";
      String email = "thanhdat@gmail.com";
      String phone ="012345678";
     Customer c = new Customer(id, name, address, email, phone);
     boolean actual = CustomerServices.checkUnique(c);
     Assertions.assertTrue(actual);
    }
}
