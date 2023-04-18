/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.oubus;

import com.oubus.pojo.Customer;
import com.oubus.services.CustomerServices;
import com.oubus.utils.MessageBox;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author bthta
 */
public class CustomersController implements Initializable {

    static CustomerServices c = new CustomerServices();

    @FXML
    TableView<Customer> tbCustomer;
    @FXML
    TextField txtName;
    @FXML
    TextField txtAddress;
    @FXML
    TextField txtEmail;
    @FXML
    TextField txtPhone;
    @FXML
    TextField txtCustomerID;
    @FXML
    TextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.LoadTableColumn();
            this.LoadTable();
            txtCustomerID.setEditable(false);
        } catch (SQLException ex) {
            Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void LoadTableColumn() {
        TableColumn colID = new TableColumn("CustomerID");
        colID.setCellValueFactory(new PropertyValueFactory("customerID"));
        colID.setPrefWidth(80);

        TableColumn colName = new TableColumn("Name");
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colName.setPrefWidth(100);

        TableColumn colAddress = new TableColumn("Address");
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colAddress.setPrefWidth(200);

        TableColumn colEmail = new TableColumn("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colEmail.setPrefWidth(150);

        TableColumn colPhone = new TableColumn("Phone Number");
        colPhone.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
        colPhone.setPrefWidth(100);

        this.tbCustomer.getColumns().setAll(colID, colName, colAddress, colEmail, colPhone);
    }

    private void LoadTable() throws SQLException {
        List<Customer> customers = c.getCustomer(null);
        this.tbCustomer.getItems().clear();
        this.tbCustomer.setItems(FXCollections.observableList(customers));
    }

    public void fetchData(MouseEvent e) {
        Customer ctm = new Customer();
        if (tbCustomer.getSelectionModel().getSelectedItem() != null) {
            ctm = tbCustomer.getSelectionModel().getSelectedItem();
            txtCustomerID.setText(ctm.getCustomerID());
            txtName.setText(ctm.getName() + "");
            txtAddress.setText(ctm.getAddress() + "");
            txtEmail.setText(ctm.getEmail() + "");
            txtPhone.setText(ctm.getPhoneNumber() + "");

        }
    }

    public void addCustomer(ActionEvent e) throws SQLException {
       
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String phoneNumber = txtPhone.getText();

        Customer cus = new Customer(name, address, email, phoneNumber);

        try {
            c.addCustomer(cus);
            MessageBox.getBox("Success", "Add customer completely", Alert.AlertType.CONFIRMATION).show();
            LoadTable();
        } catch (SQLException ex) {
            MessageBox.getBox("Fail", "Add customer failure", Alert.AlertType.WARNING).show();
            Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void DeleteCustomer( ActionEvent e) throws SQLException{
        String customerID =txtCustomerID.getText();
        String name =txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String phoneNumber = txtPhone.getText();
        
        Customer cus =new Customer(customerID, name, address, email, email);
        
        try{
            c.deleteCustomer(txtCustomerID.getText());
             MessageBox.getBox("Success", "Delete data completely", Alert.AlertType.INFORMATION).show();
             LoadTable();
        } catch (SQLException ex){
             MessageBox.getBox("Wrong", "Something wrong", Alert.AlertType.INFORMATION).show();
            Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }
    public void searchCustomerHandler(ActionEvent e) throws SQLException {

        String phoneNumber = txtSearch.getText();

        List<Customer> customers = c.getCustomer(phoneNumber);
        this.tbCustomer.getItems().clear();
        this.tbCustomer.setItems(FXCollections.observableList(customers));

    }

    public void updateCustomerHandler(ActionEvent e) throws SQLException {
        if (tbCustomer.getSelectionModel().getSelectedItem() != null) {
            Customer updatedCustomer = new Customer();
            String id = tbCustomer.getSelectionModel().getSelectedItem().getCustomerID();
            updatedCustomer.setCustomerID(id);
            updatedCustomer.setName(txtName.getText());
            updatedCustomer.setAddress(txtAddress.getText());
            updatedCustomer.setEmail(txtEmail.getText());
            updatedCustomer.setPhoneNumber(txtPhone.getText());

            try {
                c.updateCustomer(updatedCustomer);
                MessageBox.getBox("Success", "Update data completely", Alert.AlertType.INFORMATION).show();
                LoadTable();
            } catch (SQLException ex) {
                MessageBox.getBox("Fail", "Something wrong!", Alert.AlertType.ERROR).show();
                Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
