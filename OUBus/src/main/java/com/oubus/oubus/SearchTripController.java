/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.oubus;

import static com.oubus.oubus.TripController.t;
import com.oubus.pojo.Trip;
import com.oubus.utils.MessageBox;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class SearchTripController extends TripController {

    private BuyTicketsController btc;
    
    public void setMainController(BuyTicketsController btc){
        this.btc = btc;
    }
        
    @Override
    protected void loadTableColumns() {
        tbTrips.setRowFactory(e -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(evt -> {
                if (evt.getClickCount() == 2 && !row.isEmpty()) {                
                    btc.initSearchTrip(tbTrips.getSelectionModel().getSelectedItem());
                    Node source = (Node) evt.getSource();
                    Stage stage = (Stage)source.getScene().getWindow();
                    stage.close();
                    
                } else if (evt.getClickCount() == 1 && !row.isEmpty()) {
                    if (tbTrips.getSelectionModel().getSelectedItem() != null) {
                        Trip t = new Trip();
                        t = tbTrips.getSelectionModel().getSelectedItem();
                        cbBus.getSelectionModel().select(t.getBus());
                        cbDeparture.getSelectionModel().select(t.getDeparture());
                        cbDestination.getSelectionModel().select(t.getDestination());
                        cbTimeOfDeparture.getSelectionModel().select(t.getTimeOfDeparture());
                        dpDateOfDeparture.setValue(LocalDate.parse(t.getDateOfDeparture()));
                        txtPrice.setText(t.getPrice() + "");
                    }
                }
            }
            );
            return row;
        });
        
        TableColumn colTrip = new TableColumn("Trip");
        colTrip.setCellValueFactory(
                new PropertyValueFactory("tripID"));
        colTrip.setPrefWidth(
                10);
        colTrip.setStyle(
                "-fx-alignment:center;");

        TableColumn colBus = new TableColumn("Bus");

        colBus.setCellValueFactory(
                new PropertyValueFactory("bus"));
        colBus.setStyle(
                "-fx-alignment:center;");

        TableColumn colDeparture = new TableColumn("Departure");

        colDeparture.setCellValueFactory(
                new PropertyValueFactory("departure"));
        colDeparture.setStyle(
                "-fx-alignment:center;");

        TableColumn colTimeOfDeparture = new TableColumn("Time");

        colTimeOfDeparture.setCellValueFactory(
                new PropertyValueFactory("TimeOfDeparture"));
        colTimeOfDeparture.setStyle(
                "-fx-alignment:center;");

        TableColumn colDateOfDeparture = new TableColumn("Date");

        colDateOfDeparture.setCellValueFactory(
                new PropertyValueFactory("DateOfDeparture"));
        colDateOfDeparture.setStyle(
                "-fx-alignment:center;");

        TableColumn colDestination = new TableColumn("Destination");

        colDestination.setCellValueFactory(
                new PropertyValueFactory("destination"));
        colDestination.setStyle(
                "-fx-alignment:center;");

        TableColumn colPrice = new TableColumn("Price");

        colPrice.setCellValueFactory(
                new PropertyValueFactory("price"));
        colPrice.setStyle(
                "-fx-alignment:center");

        TableColumn colState = new TableColumn("State");
        colState.setCellValueFactory(new PropertyValueFactory("state"));

        TableColumn<Trip, Trip> colDelete = new TableColumn();
        colDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        colDelete.setPrefWidth(
                5);
       

        this.tbTrips.getColumns().setAll(colTrip, colBus, colDeparture, colTimeOfDeparture, colDateOfDeparture, colDestination, colPrice, colState);
    }
}
