/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.oubus;

import com.oubus.pojo.Account;
import com.oubus.pojo.Employee;
import com.oubus.services.AccountServices;
import com.oubus.services.EmployeeServices;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author PC
 */

public class EmployeeController implements Initializable{
    EmployeeServices es = new EmployeeServices();
    Employee emp = new Employee();
    AccountServices acs = new AccountServices();
    Account ac =new Account();
    
    @FXML 
    TableView<Employee> tbEmployee;
    @FXML
    TextField txtEmployeeID;
    @FXML
    TextField txtName;
    @FXML
    TextField txtCMND;
    @FXML
    TextField txtNationality;
    @FXML
    TextField txtPhone;
    @FXML
    TextField txtAddress;
    @FXML
    TextField txtEmail;
    @FXML
    DatePicker dpDateOfBirth;
    @FXML
    ComboBox<String> cbSex;
    @FXML
    ComboBox<String> cbPosition;
    @FXML
    TextField txtUsername;
    @FXML
    TextField txtPassword;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            txtEmployeeID.setEditable( false);
            //load combobox Sex
            List<String> sexlist = new ArrayList<String>();
            sexlist.add("Nam");
            sexlist.add("Nữ");
            ObservableList sList = FXCollections.observableList(sexlist);
            cbSex.setItems(sList);
            
            //load combobox Position
            List<String> positionlist = new ArrayList<String>();
            positionlist.add("Nhân viên bán vé");
            positionlist.add("Quản lí bán vé");
            positionlist.add("Trưởng phòng quản lí");
            positionlist.add("Phó phòng quản lí");
            positionlist.add("Kế toán");
            positionlist.add("Kế toán trưởng");
            positionlist.add("Giám đốc");
            ObservableList pList = FXCollections.observableList(positionlist);
            cbPosition.getItems().clear();
            cbPosition.setItems(pList);
            
 
            this.loadTableColumn();
            this.loadTableData();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadTableColumn(){
        TableColumn colUsername = new TableColumn("Username");
        colUsername.setCellValueFactory(new PropertyValueFactory("username"));
        
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
        
        this.tbEmployee.getColumns().setAll(colUsername,colEmployeeID, colName, colSex, colDoB, colNationality, colNationalID, colAddress, colEmail, colPhone, colPosition );
    }
    
    private void loadTableData() throws SQLException{
        List<Employee> employees = es.getEmployees();
        this.tbEmployee.getItems().clear();
        this.tbEmployee.setItems(FXCollections.observableList(employees));
    }
    
     public void fetchData(MouseEvent e) {
        Employee emp = new Employee();
        Account ac =new Account();
        if (tbEmployee.getSelectionModel().getSelectedItem() != null) {
            emp = tbEmployee.getSelectionModel().getSelectedItem();
            txtEmployeeID.setText(emp.getEmployeeID());
            txtName.setText(emp.getName() + "");
            txtAddress.setText(emp.getAddress() + "");
            txtEmail.setText(emp.getEmail() + "");
            txtPhone.setText(emp.getTelephone()+ "");
            txtNationality.setText(emp.getNationality() + "");
            txtCMND.setText(emp.getNationalID()+"");
            cbPosition.getSelectionModel().select(emp.getPosition());
            dpDateOfBirth.setValue(LocalDate.parse(emp.getDateOfBirth()));  
            if(emp.getSex())
                cbSex.setValue("Nam");
            else
                cbSex.setValue("Nữ");
            txtUsername.setText(ac.getAccountID());

        }
    }
    
     public void addEmployeeHandler(ActionEvent e) throws SQLException {
  
        boolean sex;
        String name = txtName.getText();
        String s = cbSex.getSelectionModel().getSelectedItem();
        if (s == "Nam")
        {
            sex = true;
        }
        else
            sex = false;
      
        String dOb = dpDateOfBirth.getValue().toString(); 
        String nationality =txtNationality.getText();
        String nationalID = txtCMND.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String phoneNumber = txtPhone.getText();
        String position = cbPosition.getSelectionModel().getSelectedItem();

        Employee emp = new Employee(name,sex,dOb,nationality,nationalID,address,email,phoneNumber,position);
        try {
            es.addEmployee(emp);
            loadTableData();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
