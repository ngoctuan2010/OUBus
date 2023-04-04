/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.oubus;

import static com.oubus.oubus.TripController.t;
import com.oubus.services.BusServices;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import com.oubus.pojo.Bus;
import com.oubus.utils.MessageBox;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author PC
 */
public class BusController implements Initializable {

    BusServices bs = new BusServices();
    @FXML
    TableView<Bus> tbBus;
    @FXML
    TextField txtName;
    @FXML
    TextField txtManufacturer;
    @FXML
    TextField txtLicense;
    @FXML
    TextField txtSeat;
    @FXML
    TextField txtType;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            this.loadTableColumn();
            this.loadTables();
        } catch (SQLException ex) {
            Logger.getLogger(BusController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTableColumn() {
        TableColumn colBusID = new TableColumn("ID");
        colBusID.setCellValueFactory(new PropertyValueFactory("busID"));
        colBusID.setPrefWidth(100);

        TableColumn colName = new TableColumn("Vehicle name");
        colName.setCellValueFactory(new PropertyValueFactory("vehicleName"));

        TableColumn colManufacturer = new TableColumn("Manufacturer");
        colManufacturer.setCellValueFactory(new PropertyValueFactory("manufacturer"));

        TableColumn colLicensePlate = new TableColumn("License plate");
        colLicensePlate.setCellValueFactory(new PropertyValueFactory("licensePlate"));

        TableColumn colSeat = new TableColumn("Total seat");
        colSeat.setCellValueFactory(new PropertyValueFactory("totalSeat"));

        TableColumn colType = new TableColumn("Type");
        colType.setCellValueFactory(new PropertyValueFactory("busType"));
        
        TableColumn colDelete = new TableColumn();
        
        
        colDelete.setCellFactory(e -> {
            Button btn = new Button("⌂");
            btn.setOnAction(evt -> {
                Alert a = MessageBox.getBox("Question", "Are you sure to delete this bus?", Alert.AlertType.CONFIRMATION);
                a.showAndWait().ifPresent((ButtonType res) -> {
                    if (res == ButtonType.OK) {
                        Button b = (Button) evt.getSource();
                        TableCell cell = (TableCell) b.getParent();
                        Bus curBus = (Bus) cell.getTableRow().getItem();

                        try {
                            if (bs.deleteBus(curBus.getBusID()) == true) {
                                MessageBox.getBox("Announcement", "Delete completely", Alert.AlertType.INFORMATION).show();
                                this.loadTables();
                            } else {
                                MessageBox.getBox("Announcement", "Delete failure", Alert.AlertType.INFORMATION).show();
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(TripController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            });

            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });

        this.tbBus.getColumns().setAll(colBusID, colName, colManufacturer, colLicensePlate, colSeat, colType, colDelete);
    }

    private void loadTables() throws SQLException {
        List<Bus> buses = bs.getBuses();

        this.tbBus.getItems().clear();
        this.tbBus.setItems(FXCollections.observableList(buses));
    }

    public void addBusHandler(ActionEvent e) {

        if ((txtName.getText() != null && !txtName.getText().isEmpty())
                && (txtManufacturer.getText() != null && !txtManufacturer.getText().isEmpty())
                && (txtLicense.getText() != null && !txtLicense.getText().isEmpty())
                && (txtSeat.getText() != null && !txtSeat.getText().isEmpty())
                && (txtType.getText() != null && !txtType.getText().isEmpty())) {

            String name = txtName.getText();
            String manufacturer = txtManufacturer.getText();
            String licensePlate = txtLicense.getText();
            int totalSeat = Integer.parseInt(txtSeat.getText());
            String type = txtType.getText();

            Bus newBus = new Bus(name, manufacturer, licensePlate, totalSeat, type);

            try {

                if (bs.addBus(newBus)) {
                    MessageBox.getBox("Success", "Add bus completely", Alert.AlertType.INFORMATION).show();
                    this.loadTables();
                } else {
                    MessageBox.getBox("Fail", "Add bus failure", Alert.AlertType.INFORMATION).show();
                }

            } catch (SQLException ex) {
                Logger.getLogger(BusController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            MessageBox.getBox("Wrong", "Enter fully infomation", Alert.AlertType.WARNING).show();
        }
    }

    public void fetchBusData(MouseEvent e) {
        if (tbBus.getSelectionModel().getSelectedItem() != null) {
            Bus curBus = tbBus.getSelectionModel().getSelectedItem();
            txtName.setText(curBus.getVehicleName());
            txtManufacturer.setText(curBus.getManufacturer());
            txtLicense.setText(curBus.getLicensePlate());
            txtSeat.setText(curBus.getTotalSeat() + "");
            txtType.setText(curBus.getBusType());
        }
    }

    public void updateBusHandler(ActionEvent e) {
        if (tbBus.getSelectionModel().getSelectedItem() != null) {
            int busID = tbBus.getSelectionModel().getSelectedItem().getBusID();
            String name = txtName.getText();
            String manufacturer = txtManufacturer.getText();
            String licensePlate = txtLicense.getText();
            int totalSeat = Integer.parseInt(txtSeat.getText());
            String type = txtType.getText();

            Bus updatedBus = new Bus(busID, name, manufacturer, licensePlate, totalSeat, type);

            try {
                if (bs.updateBus(updatedBus)) {
                    MessageBox.getBox("Success", "Updated bus completely", Alert.AlertType.INFORMATION).show();
                    this.loadTables();
                } else {
                    MessageBox.getBox("Fail", "Updated bus failure", Alert.AlertType.INFORMATION).show();
                }

            } catch (SQLException ex) {
                Logger.getLogger(BusController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            MessageBox.getBox("Wrong", "Please choose the bus nead update", Alert.AlertType.INFORMATION).show();
        }

    }

}
