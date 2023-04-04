/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.oubus;

import com.oubus.pojo.Employee;
import com.oubus.services.EmployeeServices;
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
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author PC
 */
public class EmployeeController implements Initializable{
    EmployeeServices es = new EmployeeServices();
    @FXML TableView<Employee> tbEmployee;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.loadTableColumn();
            this.loadTableData();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadTableColumn(){
        TableColumn colEmployeeID = new TableColumn("ID");
        colEmployeeID.setCellValueFactory(new PropertyValueFactory("employeeID"));
        
        TableColumn colName = new TableColumn("Name");
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        
        TableColumn colSex = new TableColumn("Sex");
        colSex.setCellValueFactory(new PropertyValueFactory("sex"));

        TableColumn colDoB = new TableColumn("Date of Birth");
        colDoB.setCellValueFactory(new PropertyValueFactory("DateOfBirth"));
        
        TableColumn colNationality = new TableColumn("Nationality");
        colNationality.setCellValueFactory(new PropertyValueFactory("nationality"));
        
        TableColumn colNationalID = new TableColumn("National ID");
        colNationalID.setCellValueFactory(new PropertyValueFactory("nationalID"));
        
        TableColumn colAddress = new TableColumn("Address");
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        
        TableColumn colEmail = new TableColumn("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        
        TableColumn colPhone = new TableColumn("Phone");
        colPhone.setCellValueFactory(new PropertyValueFactory("telephone"));
        
        TableColumn colPosition = new TableColumn("Position");
        colPosition.setCellValueFactory(new PropertyValueFactory("Position"));
        
        this.tbEmployee.getColumns().setAll(colEmployeeID, colName, colSex, colDoB, colNationality, colNationalID, colAddress, colEmail, colPhone, colPosition );
    }
    
    private void loadTableData() throws SQLException{
        List<Employee> employees = es.getEmployees();
        
        this.tbEmployee.getItems().clear();
        this.tbEmployee.setItems(FXCollections.observableList(employees));
    }
    
}
