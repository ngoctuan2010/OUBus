/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.oubus;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import com.oubus.pojo.Trip;
import com.oubus.pojo.Bus;
import com.oubus.services.TripServices;
import com.oubus.services.BusServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author PC
 */
public class TripController implements Initializable{
    static TripServices t = new TripServices();
    @FXML TableView<Trip> tbTrips;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            this.loadTableColumns();
            this.loadTables();
        }catch(SQLException ex){
            Logger.getLogger(TripController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadTableColumns(){
        TableColumn colTrip = new TableColumn("Trip");
        colTrip.setCellValueFactory(new PropertyValueFactory("tripID"));
        colTrip.setPrefWidth(50);
        
        TableColumn colBus = new TableColumn("Bus");
//        colBus.setCellValueFactory(BusServices.getLicensePlateByID());
        colBus.setPrefWidth(50);
        
        TableColumn colDeparture = new TableColumn("Departure");
        colDeparture.setCellValueFactory(new PropertyValueFactory("departure"));
        
        TableColumn colTimeOfDeparture = new TableColumn("Time");
        colTimeOfDeparture.setCellValueFactory(new PropertyValueFactory("TimeOfDeparture"));
        
        TableColumn colDateOfDeparture = new TableColumn("Date");
        colDateOfDeparture.setCellValueFactory(new PropertyValueFactory("DateOfDeparture"));
        
        TableColumn colDestination = new TableColumn("Destination");
        colDestination.setCellValueFactory(new PropertyValueFactory("destination"));   
        
        this.tbTrips.getColumns().setAll(colTrip, colBus, colDeparture, colTimeOfDeparture, colDateOfDeparture, colDestination);
    }
    
    private void loadTables() throws SQLException{
        List<Trip> trips = t.getTrip();
        
        this.tbTrips.getItems().clear();
        this.tbTrips.setItems(FXCollections.observableList(trips));
    }
}
