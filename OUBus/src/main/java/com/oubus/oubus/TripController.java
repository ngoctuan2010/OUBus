/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.oubus;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import com.oubus.pojo.Trip;
import com.oubus.pojo.Bus;
import com.oubus.pojo.Location;
import com.oubus.services.TripServices;
import com.oubus.services.BusServices;
import com.oubus.services.LocationServices;
import com.oubus.services.LocationServices;
//import com.oubus.utils.MessageBox;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author PC
 */
public class TripController implements Initializable {

    static TripServices t = new TripServices();
    @FXML
    TableView<Trip> tbTrips;
    @FXML
    TextField txtTripID;
    @FXML
    TextField txtBusID;
    @FXML
    ComboBox<Location> cbDeparture;
    @FXML
    ComboBox<Location> cbDestination;
    @FXML
    ComboBox<String> cbTime;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocationServices ls = new LocationServices();

        try {
            List<Location> locations = ls.getLocations();
            this.cbDeparture.setItems(FXCollections.observableList(locations));
            this.cbDestination.setItems(FXCollections.observableList(locations));
            this.cbTime.setItems(FXCollections.observableList(loadTimes()));

            this.loadTableColumns();
            this.loadTables();
        } catch (SQLException ex) {
            Logger.getLogger(TripController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTableColumns() {
        TableColumn colTrip = new TableColumn("Trip");
        colTrip.setCellValueFactory(new PropertyValueFactory("tripID"));
        colTrip.setPrefWidth(50);

        TableColumn colBus = new TableColumn("Bus");
        colBus.setCellValueFactory(new PropertyValueFactory("bus"));

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

    private void loadTables() throws SQLException {
        List<Trip> trips = t.getTrip();

        this.tbTrips.getItems().clear();
        this.tbTrips.setItems(FXCollections.observableList(trips));
    }

    public void addTrip(ActionEvent e) throws SQLException {
        int busID = Integer.parseInt(txtBusID.getText());
        Location departure = cbDeparture.getSelectionModel().getSelectedItem();
        Location destination = cbDestination.getSelectionModel().getSelectedItem();
        String time = cbTime.getSelectionModel().getSelectedItem();
//        MessageBox.getBox(busID + "", BusServices.getBusbyID(busID).getBusID() + "", Alert.AlertType.INFORMATION).show();

        Trip tr = new Trip(BusServices.getBusbyID(busID), departure, time, null, destination);

        if (departure.getLocationID() == destination.getLocationID()) {
//            MessageBox.getBox("Error", "Locations is not same", Alert.AlertType.WARNING).show();
        } else {
            try {
                t.addTrip(tr);
                loadTables();
            } catch (SQLException ex) {
                Logger.getLogger(TripController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void fetchData(MouseEvent e) {
        Trip t = new Trip();
        if (tbTrips.getSelectionModel().getSelectedItem() != null) {
            t = tbTrips.getSelectionModel().getSelectedItem();
            txtTripID.setText(t.getTripID()+"");
            txtBusID.setText(t.getBus().getBusID()+"");
            cbDeparture.setValue(t.getDeparture());        
            cbDestination.setValue(t.getDestination());    
            cbTime.setValue(t.getTimeOfDeparture());
        }
    }
    
    private List<String> loadTimes(){
        List<String> times = new ArrayList<>();
        times.add("6:00");
        times.add("8:00");
        times.add("10:00");
        times.add("12:00");
        times.add("14:00");
        times.add("16:00");
        return times;
    }
    

}
