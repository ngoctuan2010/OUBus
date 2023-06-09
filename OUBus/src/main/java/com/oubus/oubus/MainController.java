/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.oubus;

import com.oubus.pojo.Account;
import com.oubus.pojo.Employee;
import com.oubus.services.EmployeeServices;
import com.oubus.utils.Executor;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author bthta
 */
public class MainController {
    
    

    @FXML
    VBox scense;
    @FXML
    Label user;
    @FXML 
    Button btnLogout;
    
    @FXML
    Button btnTicket;
    @FXML
    Button btnBus;
    @FXML
    Button btnEmploy;
    @FXML
    Button btnTrip;
    @FXML
    Button btnCus;
    

    static Account cur_user;
    EmployeeServices emp = new EmployeeServices();
    static Employee employ;
    


    public void init(Account user) throws SQLException {
        this.cur_user = user;
        this.employ = emp.getEmployeeByID(cur_user.getEmployeeID());
        this.user.setText(this.employ.toString());
        
        if(cur_user.getAccessLevel() == Account.level.EMPLOYEE){
            btnBus.visibleProperty().set(false);
            btnEmploy.visibleProperty().set(false);
            btnCus.visibleProperty().set(false);
        }
        
        
    }
    
    public void loadBookTickets(ActionEvent e) throws IOException {
        VBox f = FXMLLoader.load(getClass().getResource("BookTickets_Edit.fxml"));
        scense.getChildren().setAll(f);
    }

    public void loadBus(ActionEvent e) throws IOException {
        VBox f = FXMLLoader.load(getClass().getResource("Bus.fxml"));
        scense.getChildren().setAll(f);
    }

    public void loadEmployee(ActionEvent e) throws IOException {
        VBox f = FXMLLoader.load(getClass().getResource("Employee.fxml"));
        scense.getChildren().setAll(f);
    }

    public void loadTrip(ActionEvent e) throws IOException {
        VBox f = FXMLLoader.load(getClass().getResource("Trip.fxml"));
        scense.getChildren().setAll(f);
    }

    public void loadCustomers(ActionEvent e) throws IOException {
        VBox f = FXMLLoader.load(getClass().getResource("Customers.fxml"));
        scense.getChildren().setAll(f);
    }

    public void logout(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent main = (Parent) fxmlLoader.load();     
        Stage openStage = new Stage();
        openStage.setScene(new Scene(main));
        Stage closeStage = (Stage) btnLogout.getScene().getWindow();
        closeStage.close();
        openStage.show();
    }
}
