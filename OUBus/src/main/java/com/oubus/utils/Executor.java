/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.utils;

import com.oubus.pojo.Bill;
import com.oubus.pojo.Customer;
import com.oubus.services.BillServices;
import com.oubus.services.CustomerServices;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bthta
 */
public class Executor {
    private static Executor instance;
    private ScheduledExecutorService executor;
    
    public Executor(){
        
        BillServices bs = new BillServices();
        CustomerServices cs = new CustomerServices();
        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
        try {
            List<Bill> invalidBills = bs.getInvalidBills();
            System.out.println();
            for (Bill b : invalidBills) {
                bs.deleteBill(b.getBillID());
              
                Customer c = CustomerServices.getCustomerByID(b.getCustomer().toString());
                if (c!= null && bs.getAmountTicketOfCustomer(c.getCustomerID())==0)
                    cs.deleteCustomer(c.getCustomerID());
            }
            
        } catch (SQLException ex) {
             Logger.getLogger(BillServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        }, 0,1,TimeUnit.MINUTES);
        
    }
    
    public static Executor getInstance(){
        if (instance == null){
                instance = new Executor();
        }
        return instance;
    }
    
    public void shutDownExecutor() {
        executor.shutdown();
    }
    
}
