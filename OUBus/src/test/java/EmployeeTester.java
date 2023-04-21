
import com.oubus.pojo.Customer;
import com.oubus.pojo.Employee;
import com.oubus.services.BusServices;
import com.oubus.services.JdbcUtils;
import com.oubus.services.LocationServices;
import com.oubus.services.TripServices;
import com.oubus.services.CustomerServices;
import com.oubus.services.EmployeeServices;
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
public class EmployeeTester {
    private static Connection cnn;
    private static TripServices ts;
    private static BusServices bs;
    private static LocationServices ls;
    private static CustomerServices cs;
    private static EmployeeServices es;
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
        es = new EmployeeServices();
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
    public void testAddEmployee() throws SQLException {

        String name = "Ngoc Tuan";
        boolean sex = true;
        String dOb = "4/21/2023";
        String nationlity = "VietNam";
        String nationalID = "080203444658";
        String address = "371 NK";
        String email = "ngoctuan@email.com";
        String phone = "0945681273";
        String position = "Kế toán";
       
        Employee emp = new Employee(name, sex, dOb, nationlity, nationalID, address, email, phone, position);
        
        boolean actual = es.addEmployee(emp);
        Assertions.assertTrue(actual);

    }
    
    @Test
    public void testUpdateEmployee() throws SQLException{
        String employeeID = "5fb7de99-a235-42b0-b097-a39022d69557";
        String name = "Ngoc Tuan";
        boolean sex = true;
        String dOb = "4/21/2023";
        String nationlity = "VietNam";
        String nationalID = "080203444658";
        String address = "371 NK";
        String email = "ngoctuan@email.com";
        String phone = "0945681273";
        String position = "Kế toán";
        
        Employee emp = new Employee(employeeID, name, sex, dOb, nationlity, nationalID, address, email, phone, position);
        
        boolean actual = es.updateEmployee(emp);
        Assertions.assertTrue(actual);
    }
    
    @Test
    public void testDeleteEmployee() throws SQLException{
        String employeeID = "5fb7de99-a235-42b0-b097-a39022d69557";
        boolean actual = es.deleteEmployee(employeeID);
        Assertions.assertTrue(actual);
    }
    
}
