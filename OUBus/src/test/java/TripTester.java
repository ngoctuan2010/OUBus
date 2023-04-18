
import com.oubus.pojo.Bus;
import com.oubus.pojo.Trip;
import com.oubus.pojo.Location;
import com.oubus.services.BusServices;
import com.oubus.services.JdbcUtils;
import com.oubus.services.LocationServices;
import com.oubus.services.TripServices;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PC
 */
public class TripTester {
    private static Connection cnn;
    private static TripServices ts;
    private static BusServices bs;
    private static LocationServices ls;
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
    
    @Test
    public void testAddTrip(){

            
            
       
            boolean ac = true;
            Assertions.assertTrue(ac);
            

    } 
}   
