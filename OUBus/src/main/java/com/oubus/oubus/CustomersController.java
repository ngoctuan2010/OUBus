/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.oubus;

import com.oubus.pojo.Customer;
import com.oubus.services.CustomerServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
public class CustomersController implements Initializable{
        static CustomerServices c = new CustomerServices();
        
        @FXML
        TableView<Customer> tbCustomer;
        @FXML
        TextField txtName;
        @FXML
        TextField txtAddress;
        @FXML
        TextField txtEmail;
        
        @Override
        public void initialize(URL url,ResourceBundle rb)      
        {
            try {
                this.LoadTableColumn();
                this.LoadTable();
            } catch (Exception ex) {
                Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        private  void LoadTableColumn(){
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
            
            TableColumn colPhone = new TableColumn("PhoneNumber");
            colPhone.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
            colPhone.setPrefWidth(100);
            
            this.tbCustomer.getColumns().addAll(colID,colName,colAddress,colEmail,colPhone);
        }
        
        private void LoadTable() throws SQLException{
            List<Customer> customers =c.getCustomer();
            this.tbCustomer.getItems().clear();
            this.tbCustomer.setItems(FXCollections.observableList(customers));
        }
        
        public void fetchData(MouseEvent e) {
            Customer ctm = new Customer();
            if (tbCustomer.getSelectionModel().getSelectedItem() != null) {
                ctm = tbCustomer.getSelectionModel().getSelectedItem();
                txtName.setText(ctm.getName()+"");
                txtAddress.setText(ctm.getAddress()+"");
                txtEmail.setText(ctm.getEmail()+"");        
          
            }
        }
} 
