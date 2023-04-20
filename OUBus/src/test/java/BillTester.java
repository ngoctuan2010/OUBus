/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.oubus.pojo.Bill;
import com.oubus.pojo.Bus;
import com.oubus.pojo.Customer;
import com.oubus.pojo.Employee;
import com.oubus.pojo.Location;
import com.oubus.pojo.Trip;
import com.oubus.services.BillServices;
import com.oubus.services.BusServices;
import com.oubus.services.CustomerServices;
import com.oubus.services.JdbcUtils;
import com.oubus.services.LocationServices;
import com.oubus.services.TripServices;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
/**
 *
 * @author pthin
 */
public class BillTester {
    private static Connection cnn;
    private static BillServices bis;
    private static CustomerServices cus;
    private static EmployeeServices emps;
    private static BusServices buS;
    private static LocationServices ls;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @BeforeAll
    public static void beforeAll(){
        try {
            cnn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(TripTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        buS = new BusServices();
        ls = new LocationServices();
        bis = new BillServices();
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
    public void TestAddBill() throws SQLException {
        Customer cust = cus.getCustomer(phoneNumber);
        Employee emp = new Employee("Slim",true,"2002-02-28", "VietNam", "123456789", "Village", "therealslimshady@slim.com", "01000101", "Admin");
        Bus bus = buS.getBuses().get(0);
        Location departure = ls.getLocations().get(0);
        Location destination = ls.getLocations().get(1);
        Trip trip = new Trip(bus, departure, "14:00:00", "2023-5-13" ,destination, 100000, 0);
        int seat = 1;
        Bill.statePayment state = Bill.statePayment.BOOKED;
        int price = trip.getPrice();
        java.util.Date date = Calendar.getInstance().getTime();
        String aDate = dateFormat.format(date);
        Bill bill = new Bill(cust, emp, trip, seat, state, price, aDate);
        boolean testing = bis.addBill(bill);
        Assertions.assertTrue(testing);
    }
}
