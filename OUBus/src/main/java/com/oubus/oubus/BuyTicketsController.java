/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.oubus;

import static com.oubus.oubus.CustomersController.c;
import com.oubus.pojo.Bill;
import com.oubus.pojo.Bus;
import com.oubus.pojo.Customer;
import com.oubus.pojo.Location;
import com.oubus.pojo.Trip;
import com.oubus.services.BillServices;
import com.oubus.services.BusServices;
import com.oubus.services.LocationServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author bthta
 */
public class BuyTicketsController implements Initializable{
    
    static BillServices b = new BillServices();
    
    @FXML
    TextField TimeChoice;
    @FXML
    TextField DateGo;
    @FXML
    TextField goLocation;
    @FXML
    TextField desLocation;
    @FXML
    TextField busType;
    @FXML
    TextField txtName;
    @FXML
    TextField txtEmail;
    @FXML
    TextField txtPhone;
    @FXML
    TextField seatNo;
    @FXML
    TextField txtAddress;
    @FXML
    TableView<Bill> tbBill;
    
//    private final String[] timeGo ={"0:00","0:30","1:00","1:30","2:00","2:30","3:00","3:30","4:00","4:30","5:00","5:30","6:30","7:00","7:30","8:00","8:30","9:00","9:30","10:00","10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30","18:00","18:30","19:00","19:30","20:00","20:30","21:00","21:30","22:00","22:30","23:00","23:30"};
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
//        TimeChoice.getItems().addAll(timeGo);
//         LocationServices l = new LocationServices();
//         BusServices bs = new BusServices();
       /* Get location name go */
         try {
//            
//            this.goLocation.setItems(FXCollections.observableList(localgo));
//       
//        
//            this.desLocation.setItems(FXCollections.observableList(localdes));
//        
//            this.busType.setItems(FXCollections.observableList(bus));
//            
            loadTableColumn();
            loadTable();
        } catch (SQLException ex) {
            Logger.getLogger(BuyTicketsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTableColumn() throws SQLException
    {
        TableColumn colBill = new TableColumn( "BillID");
        colBill.setCellValueFactory(new PropertyValueFactory("billID"));
        colBill.setPrefWidth(50);
        
        TableColumn colCustomer = new TableColumn("CustomerID");
        colCustomer.setCellValueFactory( new PropertyValueFactory("customer"));
        colCustomer.setPrefWidth(100);
        
        TableColumn colEmployee = new TableColumn("EmployeeID");
        colEmployee.setCellValueFactory( new PropertyValueFactory("employee"));
        colEmployee.setPrefWidth(100);
        
        TableColumn colTrip = new TableColumn("TripID");
        colTrip.setCellValueFactory( new PropertyValueFactory("trip"));
        colTrip.setPrefWidth(50);
        
        TableColumn colSeat = new TableColumn("SeatNo");
        colSeat.setCellValueFactory( new PropertyValueFactory("seat"));
        colSeat.setPrefWidth(50);
        
        TableColumn colState = new TableColumn("State");
        colState.setCellValueFactory( new PropertyValueFactory("bookingState"));
        colState.setPrefWidth(50);
        
        TableColumn colDue = new TableColumn("TotalDue");
        colDue.setCellValueFactory(new PropertyValueFactory("totalDue"));
        colDue.setPrefWidth(100);
        
        TableColumn colDate = new TableColumn("AquireDate");
        colDate.setCellValueFactory(new PropertyValueFactory("aquireDate"));
        colDate.setPrefWidth(100);
        
        this.tbBill.getColumns().setAll(colBill,colCustomer,colEmployee,colTrip,colSeat,colState,colDue,colDate);
        loadTable();
    }
    
    private void loadTable() throws SQLException{
        List<Bill> bills = b.getBill();
        this.tbBill.getItems().clear();
        this.tbBill.setItems(FXCollections.observableList(bills));
    }
    
    public void fetchData(MouseEvent e) {
        Bill bill = new Bill();
        Customer ctm = new Customer();
        Trip trip = new Trip();
        if (tbBill.getSelectionModel().getSelectedItem() != null) {
            bill = tbBill.getSelectionModel().getSelectedItem();
            ctm = tbBill.getSelectionModel().getSelectedItem().getCustomer();
            trip = tbBill.getSelectionModel().getSelectedItem().getTrip();
            TimeChoice.setText(trip.getTimeOfDeparture()+ "");
            DateGo.setText(trip.getDateOfDeparture()+ "");
            goLocation.setText(trip.getDeparture()+ "");
            desLocation.setText(trip.getDestination()+ "");
            busType.setText(trip.getBus()+ "");
            seatNo.setText(bill.getSeat()+ "");
            txtName.setText(ctm.getName() + "");
            txtAddress.setText(ctm.getAddress() + "");
            txtEmail.setText(ctm.getEmail() + "");
            txtPhone.setText(ctm.getPhoneNumber() + "");
        }
    }
}
    
