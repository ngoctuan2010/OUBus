/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.oubus.oubus;

import com.oubus.pojo.Bill;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import com.oubus.pojo.Trip;
import com.oubus.pojo.Bus;

import com.oubus.pojo.Location;
import com.oubus.services.BillServices;
import com.oubus.services.TripServices;
import com.oubus.services.BusServices;
import com.oubus.services.LocationServices;
import com.oubus.utils.MessageBox;
import java.io.IOException;
//import com.oubus.utils.MessageBox;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ChangeTripController implements Initializable {

    static TripServices t = new TripServices();
    Trip trip = new Trip(); 
    static BillServices b = new BillServices();
    @FXML
    TableView<Trip> tbTrips;
    @FXML
    ComboBox<Bus> cbBus;
    @FXML
    ComboBox<Location> cbDeparture;
    @FXML
    ComboBox<Location> cbDestination;
    @FXML
    ComboBox<String> cbTimeOfDeparture;
    @FXML
    DatePicker dpDateOfDeparture;
    @FXML
    TextField txtPrice;
    @FXML 
    Button btnChange;

    
     public void initTripInformation(Trip tr){
        this.trip = tr;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocationServices ls = new LocationServices();
        BusServices bs = new BusServices();
        try {
            List<Location> locations = ls.getLocations();
            List<Bus> buses = bs.getBuses();
            this.cbBus.setItems(FXCollections.observableList(buses));
            this.cbDeparture.setItems(FXCollections.observableList(locations));
            this.cbDestination.setItems(FXCollections.observableList(locations));
            this.cbTimeOfDeparture.setItems(FXCollections.observableList(loadTimes()));

            this.loadTableColumns();
            this.loadTables();
        } catch (SQLException ex) {
            Logger.getLogger(ChangeTripController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
    private void loadTableColumns() {
        TableColumn colTrip = new TableColumn("Trip");
        colTrip.setCellValueFactory(new PropertyValueFactory("tripID"));
        colTrip.setPrefWidth(10);
        colTrip.setStyle("-fx-alignment:center;");

        TableColumn colBus = new TableColumn("Bus");
        colBus.setCellValueFactory(new PropertyValueFactory("bus"));
        colBus.setStyle("-fx-alignment:center;");

        TableColumn colDeparture = new TableColumn("Departure");
        colDeparture.setCellValueFactory(new PropertyValueFactory("departure"));
        colDeparture.setStyle("-fx-alignment:center;");

        TableColumn colTimeOfDeparture = new TableColumn("Time");
        colTimeOfDeparture.setCellValueFactory(new PropertyValueFactory("TimeOfDeparture"));
        colTimeOfDeparture.setStyle("-fx-alignment:center;");

        TableColumn colDateOfDeparture = new TableColumn("Date");
        colDateOfDeparture.setCellValueFactory(new PropertyValueFactory("DateOfDeparture"));
        colDateOfDeparture.setStyle("-fx-alignment:center;");

        TableColumn colDestination = new TableColumn("Destination");
        colDestination.setCellValueFactory(new PropertyValueFactory("destination"));
        colDestination.setStyle("-fx-alignment:center;");
        
        TableColumn colPrice = new TableColumn("Price");
        colPrice.setCellValueFactory(new PropertyValueFactory("price"));
        colPrice.setStyle("-fx-alignment:center");

        TableColumn colDelete = new TableColumn();
        colDelete.setPrefWidth(5);
        colDelete.setCellFactory(e -> {
            Button btn = new Button("⌂");

            btn.setOnAction(evt -> {
                Alert a = MessageBox.getBox("Question", "Are you sure to delete this trip?", Alert.AlertType.CONFIRMATION);
                a.showAndWait().ifPresent((ButtonType res) -> {
                    if (res == ButtonType.OK) {
                        Button b = (Button) evt.getSource();
                        TableCell cell = (TableCell) b.getParent();
                        Trip tr = (Trip) cell.getTableRow().getItem();

                        try {
                            if (t.deleteTrip(tr.getTripID()) == true) {
                                MessageBox.getBox("Announcement", "Delete completely", Alert.AlertType.INFORMATION).show();
                                this.loadTables();
                            } else {
                                MessageBox.getBox("Announcement", "Delete failure", Alert.AlertType.INFORMATION).show();
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(ChangeTripController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            });

            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });

        this.tbTrips.getColumns().setAll(colTrip, colBus, colDeparture, colTimeOfDeparture, colDateOfDeparture, colDestination, colPrice, colDelete);
    }

    private void loadTables() throws SQLException {
        List<Trip> trips = t.getTrip();

//        this.tbTrips.set
        this.tbTrips.getItems().clear();
        this.tbTrips.setItems(FXCollections.observableList(trips));
    }

    public void addTripHandler(ActionEvent e) throws SQLException {
        Bus bus = cbBus.getSelectionModel().getSelectedItem();
        Location departure = cbDeparture.getSelectionModel().getSelectedItem();
        Location destination = cbDestination.getSelectionModel().getSelectedItem();
        String time = cbTimeOfDeparture.getSelectionModel().getSelectedItem();
        String date = dpDateOfDeparture.getValue().toString();
        int price = Integer.parseInt(txtPrice.getText());

        Trip tr = new Trip(bus, departure, time, date, destination, price,0);

        if (departure.getLocationID() == destination.getLocationID()) {
            MessageBox.getBox("Wrong", "Locations is not same", Alert.AlertType.WARNING).show();
        } else {
            if (!TripServices.checkUnique(tr)) {
                try {
                    t.addTrip(tr);
                    loadTables();
                } catch (SQLException ex) {
                    Logger.getLogger(ChangeTripController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                MessageBox.getBox("Something Wrong", "The trip is exist", Alert.AlertType.WARNING).show();
            }
        }
    }

    public void fetchDataHandler(MouseEvent e) {
        if (tbTrips.getSelectionModel().getSelectedItem() != null) {
            Trip t = new Trip();
            t = tbTrips.getSelectionModel().getSelectedItem();
            cbBus.getSelectionModel().select(t.getBus());
            cbDeparture.getSelectionModel().select(t.getDeparture());
            cbDestination.getSelectionModel().select(t.getDestination());
            cbTimeOfDeparture.getSelectionModel().select(t.getTimeOfDeparture());
            dpDateOfDeparture.setValue(LocalDate.parse(t.getDateOfDeparture()));
            txtPrice.setText(t.getPrice()+"");
        }
    }

    public void updateTripHandler(ActionEvent e) throws SQLException {
        if (tbTrips.getSelectionModel().getSelectedItem() != null) {
            Trip updatedTrip = new Trip();
            int id = tbTrips.getSelectionModel().getSelectedItem().getTripID();
            updatedTrip.setTripID(id);
            updatedTrip.setBus(cbBus.getSelectionModel().getSelectedItem());
            updatedTrip.setDeparture(cbDeparture.getSelectionModel().getSelectedItem());
            updatedTrip.setTimeOfDeparture(cbTimeOfDeparture.getSelectionModel().getSelectedItem());
            updatedTrip.setDateOfDeparture(dpDateOfDeparture.getValue().toString());
            updatedTrip.setDestination(cbDestination.getSelectionModel().getSelectedItem());
            updatedTrip.setPrice(Integer.parseInt(txtPrice.getText()));

            if (updatedTrip.getDeparture().getLocationID() == updatedTrip.getDestination().getLocationID()) {
                MessageBox.getBox("Fail", "The locations must different", Alert.AlertType.WARNING).show();
            } else if(!TripServices.checkUnique(updatedTrip)) {
                try {
                    t.updateTrip(updatedTrip);
                    MessageBox.getBox("Success", "Update data completely", Alert.AlertType.INFORMATION).show();
                    loadTables();
                } catch (SQLException ex) {
                    MessageBox.getBox("Fail", "Something wrong!", Alert.AlertType.ERROR).show();
                    Logger.getLogger(ChangeTripController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                MessageBox.getBox("Something wrong", "The trip is existed", Alert.AlertType.WARNING).show();
            }

        }
    }

    public void searchTripHandler(ActionEvent e) throws SQLException {
        Bus b = cbBus.getSelectionModel().getSelectedItem();
        Location departure = cbDeparture.getSelectionModel().getSelectedItem();
        Location destination = cbDestination.getSelectionModel().getSelectedItem();
        String tOd = cbTimeOfDeparture.getSelectionModel().getSelectedItem();
        String dOd = "";
        if (dpDateOfDeparture.getValue() != null) {
            dOd = dpDateOfDeparture.getValue().toString();

        }

        List<Trip> trips = t.searchTrip(b, departure, destination, tOd, dOd);
        this.tbTrips.getItems().clear();
        this.tbTrips.setItems(FXCollections.observableList(trips));

    }

    public void reload(ActionEvent e) throws SQLException {
        loadTables();
        cbBus.getSelectionModel().select(null);
        cbDeparture.getSelectionModel().select(null);
        cbDestination.getSelectionModel().select(null);
        cbTimeOfDeparture.getSelectionModel().select(null);
        dpDateOfDeparture.setValue(null);
        txtPrice.setText(null);
    }
    
    private void ChangeTripHandler( ActionEvent e) throws SQLException{ 
        if (tbTrips.getSelectionModel().getSelectedItem() != null) {
            Bill updatedBill = new Bill();
            int id = tbTrips.getSelectionModel().getSelectedItem().getTripID();
            
           
          
           
                try {
                    b.updateBill(updatedBill);
                    MessageBox.getBox("Success", "Update data completely", Alert.AlertType.INFORMATION).show();
                    loadTables();
                } catch (SQLException ex) {
                    MessageBox.getBox("Fail", "Something wrong!", Alert.AlertType.ERROR).show();
                    Logger.getLogger(TripController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
           

        }
    }
    
    
    private List<String> loadTimes() {
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
