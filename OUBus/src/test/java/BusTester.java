import com.oubus.pojo.Bus;
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
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bthta
 */
public class BusTester {
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
    
    @Test
    public void TestAddBus() throws SQLException{
        String name ="Airblade";
        String manufaturer ="Yamaha";
        String licensePlate ="35-T14 1407";
        int totalSeat = 40;
        String busType ="Giuong nam";
        Bus b = new Bus(name, manufaturer, licensePlate, totalSeat, busType);
        boolean actual = bs.addBus(b);
        Assertions.assertTrue(actual);
    }
    
    @Test
    public void TestDeleteBus() throws SQLException{
        int id = 8;
        boolean actual = bs.deleteBus(id);
        Assertions.assertTrue(actual);
    }
    @Test
    public void TestUpdateBus() throws SQLException{
        int id = 9;
        String name ="Airblade";
        String manufaturer ="Yamaha";
        String licensePlate ="35-T14 0702";
        int totalSeat = 40;
        String busType ="Giuong nam";
        Bus b = new Bus(id, name, manufaturer, licensePlate, totalSeat, busType);
        
        boolean actual = bs.updateBus(b);
        Assertions.assertTrue(actual);
    }
    
    
}
