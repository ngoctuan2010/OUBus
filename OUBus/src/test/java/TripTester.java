
import com.oubus.pojo.Bus;
import com.oubus.pojo.Trip;
import com.oubus.pojo.Location;
import com.oubus.services.BusServices;
import com.oubus.services.JdbcUtils;
import com.oubus.services.LocationServices;
import com.oubus.services.TripServices;
import java.sql.*;
import java.util.List;
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
    public void testAddTrip() throws SQLException{
        Bus bus = bs.getBuses().get(0);
        Location departure = ls.getLocations().get(0);
        Location destination = ls.getLocations().get(1);
        Trip tr = new Trip(bus, departure, "14:00:00", "2023-5-13" ,destination, 100000, 0);
        
        boolean actual = ts.addTrip(tr);
        Assertions.assertTrue(actual);     
    } 
    
    @Test
    public void testUpdateTrip() throws SQLException{
        int id = 46;
        Bus bus = bs.getBuses().get(1);
        Location departure = ls.getLocations().get(1);
        Location destination = ls.getLocations().get(0);
        int price = 200000;
        int state = 1;
        
        Trip tr = new Trip(id, bus, departure, "18:00:00", "2023-5-11", destination, price, state);
        boolean actual = ts.updateTrip(tr);
        Assertions.assertTrue(actual);
        
    }
    
    @Test
    public void testDeleteTrip() throws SQLException{
        int id = 48;
        boolean actual = ts.deleteTrip(id);
        Assertions.assertTrue(actual);
    
    }
    
    @Test
    public void testSearchTrip() throws SQLException{
        Bus bus = bs.getBuses().get(0);
        Location departure = ls.getLocations().get(0);
        Location destination = ls.getLocations().get(1);
        List<Trip> trips = ts.searchTrip(bus, departure, destination, "14:00", "2023-05-13");
        
        Assertions.assertTrue(trips.size() == 4);
    }
}   
