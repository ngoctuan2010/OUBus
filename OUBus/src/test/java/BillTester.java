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
import com.oubus.services.EmployeeServices;
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
    private static TripServices ts;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @BeforeAll
    public static void beforeAll(){
        try {
            cnn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(TripTester.class.getName()).log(Level.SEVERE, null, ex);
        }

        bis = new BillServices();
        cus = new CustomerServices();
        emps =new EmployeeServices();
        ts = new TripServices();
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
        Customer cust = cus.getCustomerByPhone("0963841763");
        Employee emp = emps.getEmployeeByID("8e8e36e1-f078-4f2b-baa8-ac3882c11cbd");
        Trip trip = ts.getTripByID(2);
        int seat = 1;
        Bill.statePayment state = Bill.statePayment.BOOKED;
        int price = trip.getPrice();
        java.util.Date date = Calendar.getInstance().getTime();
        String aDate = dateFormat.format(date);
        Bill bill = new Bill(cust, emp, trip, seat, state, price, aDate);
        boolean testing = bis.addBill(bill);
        Assertions.assertTrue(testing);
    }
    
    @Test
    public void TestUpdateBill() throws SQLException {
        Customer cust = cus.getCustomerByPhone("0963841763");
        Employee emp = emps.getEmployeeByID("8e8e36e1-f078-4f2b-baa8-ac3882c11cbd");
        Trip trip = ts.getTripByID(2);
        int seat = 1;
        Bill.statePayment state = Bill.statePayment.PAID;
        int price = trip.getPrice();
        java.util.Date date = Calendar.getInstance().getTime();
        String aDate = dateFormat.format(date);
        Bill bill = new Bill(cust, emp, trip, seat, state, price, aDate);
        boolean testing = bis.updateBill(bill);
        Assertions.assertTrue(testing);
    }
    
    @Test
   public void TestDeleteBill() throws SQLException {
       
        Bill bill = bis.getBill().get(0);
        boolean testing = bis.deleteBill(bill.getBillID());
        Assertions.assertTrue(testing);
    }
}
