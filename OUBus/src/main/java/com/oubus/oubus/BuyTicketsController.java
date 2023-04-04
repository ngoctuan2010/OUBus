/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.oubus;

import com.oubus.pojo.Bus;
import com.oubus.pojo.Location;
import com.oubus.services.BusServices;
import com.oubus.services.LocationServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 *
 * @author bthta
 */
public class BuyTicketsController implements Initializable{
    @FXML
    private ComboBox<String> TimeChoiceBox;
    @FXML
    private ComboBox<Location> goLocation;
    @FXML
    private ComboBox<Location> desLocation;
    @FXML
    private ComboBox<Bus> busType;
    private String[] timeGo ={"0:00","0:30","1:00","1:30","2:00","2:30","3:00","3:30","4:00","4:30","5:00","5:30","6:30","7:00","7:30","8:00","8:30","9:00","9:30","10:00","10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30","18:00","18:30","19:00","19:30","20:00","20:30","21:00","21:30","22:00","22:30","23:00","23:30"};
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        TimeChoiceBox.getItems().addAll(timeGo);
         LocationServices l = new LocationServices();
         BusServices b = new BusServices();
       /* Get location name go */
         try {
            List<Location> local = l.getLocations();
            this.goLocation.setItems(FXCollections.observableList(local));
        } catch (SQLException ex) {
            Logger.getLogger(BuyTicketsController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
          /* Get location name des */
         try {
            List<Location> local = l.getLocations();
            this.desLocation.setItems(FXCollections.observableList(local));
        } catch (SQLException ex) {
            Logger.getLogger(BuyTicketsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*Get Name Bus*/
          try {
            List<Bus> bus =b.getBuses();
            this.busType.setItems(FXCollections.observableList(bus));
        } catch (SQLException ex) {
            Logger.getLogger(BuyTicketsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
