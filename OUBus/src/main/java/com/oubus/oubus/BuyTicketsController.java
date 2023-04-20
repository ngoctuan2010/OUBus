/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.oubus;

import static com.oubus.oubus.MainController.employ;
import com.oubus.pojo.Bill;

import com.oubus.pojo.Customer;
import com.oubus.pojo.Employee;
import com.oubus.pojo.Trip;
import com.oubus.services.BillServices;
import com.oubus.services.CustomerServices;
import com.oubus.services.RuleSetServices;
import com.oubus.services.TripServices;
import com.oubus.utils.MessageBox;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
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
    static TripServices t = new TripServices();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
    @FXML
    Button btnCheck;
    @FXML
    Button btnChangeTrip;

    public void initTrip(Trip trip) throws SQLException {
        TimeChoice.setText(trip.getTimeOfDeparture() + "");
        DateGo.setText(trip.getDateOfDeparture() + "");
        goLocation.setText(trip.getDeparture() + "");
        desLocation.setText(trip.getDestination() + "");
        busType.setText(trip.getBus().getVehicleName() + "");

        this.orderTrip = trip;
        List<Bill> bills = b.getBillByTripID(trip.getTripID());
        this.loadTable(bills);

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
            busType.setText(trip.getBus().getVehicleName() + "");
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
        Bill.statePayment bookingState = Bill.statePayment.PAID;
        double totalDue = orderTrip.getPrice();
        java.util.Date date = Calendar.getInstance().getTime();
        String aDate = dateFormat.format(date);

        Customer cus = new Customer(name, address, email, phone);

        if (!CustomerServices.checkUnique(cus)) {
            try {
                c.addCustomer(cus);

            } catch (SQLException ex) {
                Logger.getLogger(CustomerServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            cus = CustomerServices.getCustomerByPhone(phone);

        }

        Bill bill = new Bill(cus, employ, orderTrip, seat, bookingState, totalDue, aDate);
        String tripTime = bill.getTrip().getDateOfDeparture() + " " + bill.getTrip().getTimeOfDeparture() + ":00";
        if (RuleSetServices.CheckTime(RuleSetServices.timeCalculator(aDate, tripTime), 300)) {

            if (!BillServices.checkSeatUnique(bill) && BillServices.checkOverSeat(bill)) {
                try {
                    b.addBill(bill);

                    loadTableColumn();
                    this.loadTable(b.getBillByTripID(bill.getTrip().getTripID()));

                    MessageBox.getBox("SUCCESS", "Đã mua vé thành công!!!", Alert.AlertType.INFORMATION).show();

                } catch (SQLException ex) {
                    Logger.getLogger(CustomerServices.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                MessageBox.getBox("ERROR", "Vé này đã tồn tại!!!", Alert.AlertType.INFORMATION).show();
            }
        } else {
            MessageBox.getBox("ERROR", "Đã hết thời gian tương tác!!!", Alert.AlertType.ERROR).show();
        }
    }

    public void bookBillHandler(ActionEvent e) throws SQLException {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        int seat = parseInt(seatNo.getText());
        Bill.statePayment bookingState = Bill.statePayment.BOOKED;
        double totalDue = orderTrip.getPrice();
        java.util.Date date = Calendar.getInstance().getTime();
        String aDate = dateFormat.format(date);

        Customer cus = new Customer(name, address, email, phone);

        if (!CustomerServices.checkUnique(cus)) {
            try {
                c.addCustomer(cus);
            } catch (SQLException ex) {
                Logger.getLogger(CustomerServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            cus = CustomerServices.getCustomerByPhone(phone);
        }

        Bill bill = new Bill(cus, employ, orderTrip, seat, bookingState, totalDue, aDate);
        String tripTime = bill.getTrip().getDateOfDeparture() + " " + bill.getTrip().getTimeOfDeparture() + ":00";
        if (RuleSetServices.CheckTime(RuleSetServices.timeCalculator(aDate, tripTime), 3600)) {
            if (!BillServices.checkSeatUnique(bill) && BillServices.checkOverSeat(bill)) {
                try {
                    b.addBill(bill);
                    List<Bill> bills = b.getBill();
                    MessageBox.getBox("SUCCESS", "Đã đặt vé thành công!!!", Alert.AlertType.INFORMATION).show();
                    loadTable(bills);
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerServices.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                MessageBox.getBox("ERROR", "Vé này đã tồn tại!!!", Alert.AlertType.INFORMATION).show();
            }
        } else {
            MessageBox.getBox("ERROR", "Đã hết thời gian tương tác!!!", Alert.AlertType.ERROR).show();
        }
    }

    public void deleteBillHandler(ActionEvent e) throws SQLException {
        Bill bill = tbBill.getSelectionModel().getSelectedItem();
        java.util.Date date = Calendar.getInstance().getTime();
        String aDate = dateFormat.format(date);
        String tripTime = bill.getTrip().getDateOfDeparture() + " " + bill.getTrip().getTimeOfDeparture() + ":00";

        if (RuleSetServices.CheckTime(RuleSetServices.timeCalculator(aDate, tripTime), 300)) {
            if (RuleSetServices.CheckTime(RuleSetServices.timeCalculator(aDate, tripTime), 1800)) {
                if (BillServices.checkExist(bill)) {
                    try {
                        b.deleteBill(bill.getBillID());
                        List<Bill> bills = b.getBill();
                        loadTable(bills);
                    } catch (SQLException ex) {
                        Logger.getLogger(BillServices.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    MessageBox.getBox("SUCCESS", "Đã huỷ vé thành công!!!", Alert.AlertType.INFORMATION).show();
                } else {
                    MessageBox.getBox("ERROR", "Vé này đã tồn tại!!!", Alert.AlertType.INFORMATION).show();
                }
            } else {
                Bill.statePayment bookingState = Bill.statePayment.CANCELLED;
                bill.setBookingState(bookingState);
                bill.setAquiredDate(aDate);
                if (BillServices.checkExist(bill)) {
                    try {
                        b.updateBill(bill);
                        List<Bill> bills = b.getBill();
                        loadTable(bills);
                    } catch (SQLException ex) {
                        Logger.getLogger(BillServices.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    MessageBox.getBox("SUCCESS", "Đã huỷ vé thành công!!!", Alert.AlertType.INFORMATION).show();
                } else {
                    MessageBox.getBox("ERROR", "Vé này đã tồn tại!!!", Alert.AlertType.INFORMATION).show();
                }

            }
        } else {
            MessageBox.getBox("ERROR", "Đã hết thời gian tương tác!!!", Alert.AlertType.ERROR).show();
        }
    }

    public void getBillHandler(ActionEvent e) throws SQLException {
        Bill bill = tbBill.getSelectionModel().getSelectedItem();
        if (bill.getBookingState() == Bill.statePayment.BOOKED) {
            java.util.Date date = Calendar.getInstance().getTime();
            String aDate = dateFormat.format(date);
            Bill.statePayment bookingState = Bill.statePayment.PAID;
            bill.setBookingState(bookingState);
            bill.setAquiredDate(aDate);
            try {
                b.updateBill(bill);
                List<Bill> bills = b.getBill();
                loadTable(bills);
            } catch (SQLException ex) {
                Logger.getLogger(BillServices.class.getName()).log(Level.SEVERE, null, ex);
            }
            MessageBox.getBox("SUCCESS", "Đã xuất vé thành công!!!", Alert.AlertType.INFORMATION).show();
        } else {
            MessageBox.getBox("FAIL", "Vé này đã bị huỷ!!!", Alert.AlertType.ERROR).show();
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
            String id = btnTrip.getText();
            Trip tr;
            if (!id.contains("Tìm chuyến")) {
                tr = ts.getTripByID(Integer.parseInt(id));
            } else {
                tr = null;
            }

            List<Bill> bills = bs.searchBill(sCus, tr);
            loadTable(bills);
        }

    }

    public void updateBillHandler(ActionEvent e) throws SQLException {
        if (tbBill.getSelectionModel().getSelectedItem() != null) {
            Bill bill = new Bill();
            Customer cus = new Customer();
            Employee emp = new Employee();

            Trip trip = new Trip();

            String id = tbBill.getSelectionModel().getSelectedItem().getBillID();
            Customer cusID = tbBill.getSelectionModel().getSelectedItem().getCustomer();
            Employee emID = tbBill.getSelectionModel().getSelectedItem().getEmployee();
            Trip tripID = tbBill.getSelectionModel().getSelectedItem().getTrip();
            bill.setBillID(id);
            bill.setCustomer(cusID);
            bill.setEmployee(emID);
            bill.setTrip(tripID);
            bill.setSeat(seatNo.getLength());
            bill.setBookingState(tbBill.getSelectionModel().getSelectedItem().getBookingState());
            bill.setTotalDue(tbBill.getSelectionModel().getSelectedItem().getTotalDue());
            bill.setAquiredDate(tbBill.getSelectionModel().getSelectedItem().getAquiredDate());

            cus.setCustomerID(cusID.getCustomerID());
            cus.setName(txtName.getText());
            cus.setEmail(txtEmail.getText());
            cus.setPhoneNumber(txtPhone.getText());
            cus.setAddress(txtAddress.getText());

            try {
                c.updateCustomer(cus);
                b.updateBill(bill);
                MessageBox.getBox("Success", "Update data completely", Alert.AlertType.INFORMATION).show();
                List<Bill> bills = b.getBill();
                loadTable(bills);
            } catch (SQLException ex) {
                MessageBox.getBox("Fail", "Something wrong!", Alert.AlertType.ERROR).show();
                Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void ChangeTripBill(ActionEvent e) throws SQLException {
        if (tbBill.getSelectionModel().getSelectedItem() != null) {
            Bill bill = new Bill();
            Customer cus = new Customer();
            // Employee emp = new Employee();
            java.util.Date date = Calendar.getInstance().getTime();
            String aDate = dateFormat.format(date);

            Trip trip = new Trip();

            String id = tbBill.getSelectionModel().getSelectedItem().getBillID();
            Customer cusID = tbBill.getSelectionModel().getSelectedItem().getCustomer();
            Employee emID = tbBill.getSelectionModel().getSelectedItem().getEmployee();
            Trip thisTrip = tbBill.getSelectionModel().getSelectedItem().getTrip();
            //  Trip tripID =tbBill.getSelectionModel().getSelectedItem().getTrip();

            Bill checkbill = new Bill();
            checkbill.setBillID(tbBill.getSelectionModel().getSelectedItem().getBillID());
            checkbill.setCustomer(cusID);
            checkbill.setEmployee(emID);
            checkbill.setTrip(thisTrip);
            checkbill.setSeat(parseInt(seatNo.getText()));
            checkbill.setBookingState(tbBill.getSelectionModel().getSelectedItem().getBookingState());
            checkbill.setTotalDue(tbBill.getSelectionModel().getSelectedItem().getTotalDue());
            checkbill.setAquiredDate(tbBill.getSelectionModel().getSelectedItem().getAquiredDate());

            cus.setCustomerID(cusID.getCustomerID());
            cus.setName(txtName.getText());
            cus.setEmail(txtEmail.getText());
            cus.setPhoneNumber(txtPhone.getText());
            cus.setAddress(txtAddress.getText());

            String tripTime = checkbill.getTrip().getDateOfDeparture() + " " + checkbill.getTrip().getTimeOfDeparture() + ":00";
            if (RuleSetServices.CheckTime(RuleSetServices.timeCalculator(aDate, tripTime), 3600)) {
                try {
                    Trip tripID = t.getTripByID(parseInt(btnTrip.getText()));
                    bill.setBillID(id);
                    bill.setCustomer(cusID);
                    bill.setEmployee(emID);
                    bill.setTrip(tripID);
                    bill.setSeat(parseInt(seatNo.getText()));
                    bill.setBookingState(tbBill.getSelectionModel().getSelectedItem().getBookingState());
                    bill.setTotalDue(tbBill.getSelectionModel().getSelectedItem().getTotalDue());
                    bill.setAquiredDate(tbBill.getSelectionModel().getSelectedItem().getAquiredDate());
                    c.updateCustomer(cus);
                    b.updateBill(bill);
                    MessageBox.getBox("Success", "Change Trip completely", Alert.AlertType.INFORMATION).show();
                    List<Bill> bills = b.getBill();
                    loadTable(bills);
                } catch (SQLException ex) {
                    MessageBox.getBox("Fail", "Something wrong!", Alert.AlertType.ERROR).show();
                    Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                MessageBox.getBox("", "Khong duoc doi ve sau 60 phut ", Alert.AlertType.ERROR).show();

            }
        }
    }

    public void CheckExitSeat(ActionEvent e) throws SQLException {
        int seat = parseInt(seatNo.getText());
        String tripID = btnTrip.getText();

        if (BillServices.checkSeatBill(seat, tripID)) {
            MessageBox.getBox("", "Ghế đã tồn tại", Alert.AlertType.INFORMATION).show();
        } else {
            MessageBox.getBox("", "Ghế đang trống", Alert.AlertType.INFORMATION).show();
        }
    }
}
