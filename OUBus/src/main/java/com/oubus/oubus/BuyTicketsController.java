/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.oubus;

import static com.oubus.oubus.CustomersController.c;
import static com.oubus.oubus.TripController.t;
import static com.oubus.oubus.MainController.employ;
import com.oubus.pojo.Bill;
import com.oubus.pojo.Bill.statePayment;
import static com.oubus.pojo.Bill.statePayment.BOOKED;
import static com.oubus.pojo.Bill.statePayment.CANCELLED;
import static com.oubus.pojo.Bill.statePayment.PAID;
import com.oubus.pojo.Bus;
import com.oubus.pojo.Customer;
import com.oubus.pojo.Location;
import com.oubus.pojo.Trip;
import com.oubus.services.BillServices;
import static com.oubus.services.BillServices.checkExist;
import static com.oubus.services.BillServices.checkSeatUnique;
import com.oubus.services.BusServices;
import com.oubus.services.CustomerServices;
import static com.oubus.services.CustomerServices.checkUnique;
import com.oubus.services.LocationServices;
import com.oubus.services.TripServices;
import com.oubus.utils.MessageBox;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author bthta
 */
public class BuyTicketsController implements Initializable {

//    private TripController tc;
    static BillServices b = new BillServices();
    static CustomerServices c = new CustomerServices();
    private Trip searchingTrip;
    private Trip orderTrip;

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
    TextField txtSearch;
    @FXML
    TableView<Bill> tbBill;
    @FXML
    Button btnTrip;

    public void initTrip(Trip trip) {
        TimeChoice.setText(trip.getTimeOfDeparture() + "");
        DateGo.setText(trip.getDateOfDeparture() + "");
        goLocation.setText(trip.getDeparture() + "");
        desLocation.setText(trip.getDestination() + "");
        busType.setText(trip.getBus() + "");

        this.orderTrip = trip;

    }

    public void initSearchTrip(Trip trip) {
        this.searchingTrip = trip;
        btnTrip.setText(searchingTrip.getTripID() + "");
    }

    private final String[] timeGo = {"0:00", "0:30", "1:00", "1:30", "2:00", "2:30", "3:00", "3:30", "4:00", "4:30", "5:00", "5:30", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            loadTableColumn();
            List<Bill> bills = b.getBill();
            loadTable(bills);

        } catch (SQLException ex) {
            Logger.getLogger(BuyTicketsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTableColumn() throws SQLException {
        tbBill.setRowFactory(e -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(evt -> {
                if (evt.getClickCount() == 1 && !row.isEmpty()) {
                    Bill bill = new Bill();
                    Customer ctm = new Customer();
                    Trip trip = new Trip();
                    if (tbBill.getSelectionModel().getSelectedItem() != null) {
                        bill = tbBill.getSelectionModel().getSelectedItem();
                        ctm = tbBill.getSelectionModel().getSelectedItem().getCustomer();
                        trip = tbBill.getSelectionModel().getSelectedItem().getTrip();
                        TimeChoice.setText(trip.getTimeOfDeparture() + "");
                        DateGo.setText(trip.getDateOfDeparture() + "");
                        goLocation.setText(trip.getDeparture() + "");
                        desLocation.setText(trip.getDestination() + "");
                        busType.setText(trip.getBus() + "");
                        seatNo.setText(bill.getSeat() + "");
                        txtName.setText(ctm.getName() + "");
                        txtAddress.setText(ctm.getAddress() + "");
                        txtEmail.setText(ctm.getEmail() + "");
                        txtPhone.setText(ctm.getPhoneNumber() + "");
                    }
                }
            });

            return row;
        });

        TableColumn colBill = new TableColumn("BillID");
        colBill.setCellValueFactory(new PropertyValueFactory("billID"));
        colBill.setPrefWidth(50);

        TableColumn colCustomer = new TableColumn("CustomerID");
        colCustomer.setCellValueFactory(new PropertyValueFactory("customer"));
        colCustomer.setPrefWidth(100);

        TableColumn colEmployee = new TableColumn("EmployeeID");
        colEmployee.setCellValueFactory(new PropertyValueFactory("employee"));
        colEmployee.setPrefWidth(100);

        TableColumn colTrip = new TableColumn("TripID");
        colTrip.setCellValueFactory(new PropertyValueFactory("trip"));
        colTrip.setPrefWidth(50);

        TableColumn colSeat = new TableColumn("SeatNo");
        colSeat.setCellValueFactory(new PropertyValueFactory("seat"));
        colSeat.setPrefWidth(50);

        TableColumn colState = new TableColumn("State");
        colState.setCellValueFactory(new PropertyValueFactory("bookingState"));
        colState.setPrefWidth(50);

        TableColumn colDue = new TableColumn("TotalDue");
        colDue.setCellValueFactory(new PropertyValueFactory("totalDue"));
        colDue.setPrefWidth(100);

        TableColumn colDate = new TableColumn("AquireDate");
        colDate.setCellValueFactory(new PropertyValueFactory("aquiredDate"));
        colDate.setPrefWidth(200);

        this.tbBill.getColumns().setAll(colBill, colCustomer, colEmployee, colTrip, colSeat, colState, colDue, colDate);

    }

    private void loadTable(List<Bill> bill) throws SQLException {

        this.tbBill.getItems().clear();
        this.tbBill.setItems(FXCollections.observableList(bill));
    }

    public void fetchData(MouseEvent e) {
        Bill bill = new Bill();
        Customer ctm = new Customer();
        Trip trip = new Trip();
        if (tbBill.getSelectionModel().getSelectedItem() != null) {
            bill = tbBill.getSelectionModel().getSelectedItem();
            ctm = tbBill.getSelectionModel().getSelectedItem().getCustomer();
            trip = tbBill.getSelectionModel().getSelectedItem().getTrip();
            TimeChoice.setText(trip.getTimeOfDeparture() + "");
            DateGo.setText(trip.getDateOfDeparture() + "");
            goLocation.setText(trip.getDeparture() + "");
            desLocation.setText(trip.getDestination() + "");
            busType.setText(trip.getBus() + "");
            seatNo.setText(bill.getSeat() + "");
            txtName.setText(ctm.getName() + "");
            txtAddress.setText(ctm.getAddress() + "");
            txtEmail.setText(ctm.getEmail() + "");
            txtPhone.setText(ctm.getPhoneNumber() + "");
        }
    }

    public void fetchCustomerHandler(ActionEvent e) throws SQLException {
        Customer ctm = CustomerServices.getCustomerByPhone(txtPhone.getText());
        txtName.setText(ctm.getName() + "");
        txtAddress.setText(ctm.getAddress() + "");
        txtEmail.setText(ctm.getEmail() + "");
    }

    public void addBillHandler(ActionEvent e) throws SQLException {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        int seat = parseInt(seatNo.getText());
        statePayment bookingState = PAID;
        double totalDue = orderTrip.getPrice();
        java.util.Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String aDate = dateFormat.format(date);

        Customer cus = new Customer(name, address, email, phone);

        if (!checkUnique(cus)) {
            try {
                c.addCustomer(cus);
                loadTableColumn();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            cus = CustomerServices.getCustomerByPhone(phone);
        }

        Bill bill = new Bill(cus, employ, orderTrip, seat, bookingState, totalDue, aDate);

        if (!checkSeatUnique(bill)) {
            try {
                b.addBill(bill);
                loadTableColumn();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            MessageBox.getBox("Bruh", "Có vé nì rồi ní!!!", Alert.AlertType.INFORMATION).show();
        }
    }

    public void orderBillHandler(ActionEvent e) throws SQLException {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        int seat = parseInt(seatNo.getText());
        statePayment bookingState = BOOKED;
        double totalDue = orderTrip.getPrice();
        java.util.Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String aDate = dateFormat.format(date);

        Customer cus = new Customer(name, address, email, phone);

        if (!checkUnique(cus)) {
            try {
                c.addCustomer(cus);
                loadTableColumn();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            cus = CustomerServices.getCustomerByPhone(phone);
        }

        Bill bill = new Bill(cus, employ, orderTrip, seat, bookingState, totalDue, aDate);

        if (!checkSeatUnique(bill)) {
            try {
                b.addBill(bill);
                loadTableColumn();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            MessageBox.getBox("Bruh", "Có vé nì rồi ní!!!", Alert.AlertType.INFORMATION).show();
        }
    }

    public void deleteBillHandler(ActionEvent e) throws SQLException {
        statePayment bookingState = CANCELLED;
        java.util.Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String aDate = dateFormat.format(date);

        Bill bill = tbBill.getSelectionModel().getSelectedItem();
        bill.setBookingState(bookingState);
        bill.setAquiredDate(aDate);
        if (checkExist(bill)) {
            try {
                b.updateBill(bill);
                loadTableColumn();
            } catch (SQLException ex) {
                Logger.getLogger(BillServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            MessageBox.getBox("Bruh", "Có vé nì đâu ní!!!", Alert.AlertType.INFORMATION).show();
        }
    }

    public void searchTripHandler(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Trip_Search.fxml"));
        Parent ts = loader.load();
        SearchTripController stc = loader.getController();
        stc.setMainController(this);

        Stage newStage = new Stage();
        newStage.setScene(new Scene(ts));
        newStage.show();

    }

    public void searchBillHandler(ActionEvent e) throws SQLException {
//            String search = txtSearch.getText();
//            
//            Customer cus = CustomerServices.getCustomerByPhone(search);
//            BillServices.searchBillByCus(cus);

        BillServices bs = new BillServices();
        CustomerServices cs = new CustomerServices();
        TripServices ts = new TripServices();

        Customer sCus = null;
        if (txtSearch.getText() != null && !txtSearch.getText().isEmpty()) {
            sCus = cs.getCustomer(txtSearch.getText()).get(0);
        }

        String id = btnTrip.getText();
        Trip tr;
        if (!id.contains("Tìm chuyến")) {
            MessageBox.getBox("A", id, Alert.AlertType.INFORMATION).show();
            tr = TripServices.getTripByID(Integer.parseInt(id));
        } else {
            tr = null;
        }

        List<Bill> bills = bs.searchBill(sCus, tr);
        loadTable(bills);

    }

}
