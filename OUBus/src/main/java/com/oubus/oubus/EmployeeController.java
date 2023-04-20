/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.oubus;

import com.oubus.pojo.Account;
import com.oubus.pojo.Employee;
import com.oubus.services.AccountServices;
import com.oubus.services.EmployeeServices;
import com.oubus.utils.MessageBox;
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
import javafx.scene.control.Alert;
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
public class EmployeeController implements Initializable {

    EmployeeServices es = new EmployeeServices();
    Employee emp = new Employee();
    AccountServices acs = new AccountServices();
    Account ac = new Account();

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
            txtEmployeeID.setEditable(false);
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

    private void loadTableColumn() {
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

        this.tbEmployee.getColumns().setAll(colEmployeeID, colName, colSex, colDoB, colNationality, colNationalID, colAddress, colEmail, colPhone, colPosition);
    }

    private void loadTableData() throws SQLException {
        List<Employee> employees = es.getEmployees();
        this.tbEmployee.getItems().clear();
        this.tbEmployee.setItems(FXCollections.observableList(employees));
    }

    public void fetchData(MouseEvent e) throws SQLException {
        AccountServices ac = new AccountServices();
        Employee emp = new Employee();
        Account acc = ac.getAccountByEmployee(txtEmployeeID.getText());
        if (tbEmployee.getSelectionModel().getSelectedItem() != null) {
            emp = tbEmployee.getSelectionModel().getSelectedItem();
            txtEmployeeID.setText(emp.getEmployeeID());
            txtName.setText(emp.getName() + "");
            txtAddress.setText(emp.getAddress() + "");
            txtEmail.setText(emp.getEmail() + "");
            txtPhone.setText(emp.getTelephone() + "");
            txtNationality.setText(emp.getNationality() + "");
            txtCMND.setText(emp.getNationalID() + "");
            cbPosition.getSelectionModel().select(emp.getPosition());
            dpDateOfBirth.setValue(LocalDate.parse(emp.getDateOfBirth()));
            if (emp.getSex()) {
                cbSex.setValue("Nam");
            } else {
                cbSex.setValue("Nữ");
            }
//            txtUsername.setText(acc.getUsername());
//            txtPassword.setText(acc.getPassword());

        }
    }

    public void addEmployeeHandler(ActionEvent e) throws SQLException {

        boolean sex;
        String name = txtName.getText();
        String s = cbSex.getSelectionModel().getSelectedItem();
        if (s == "Nam") {
            sex = true;
        } else {
            sex = false;
        }

        String dOb = dpDateOfBirth.getValue().toString();
        String nationality = txtNationality.getText();
        String nationalID = txtCMND.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String phoneNumber = txtPhone.getText();
        String position = cbPosition.getSelectionModel().getSelectedItem();

        Employee emp = new Employee(name, sex, dOb, nationality, nationalID, address, email, phoneNumber, position);
        try {
            es.addEmployee(emp);
            Employee employ = es.getEmployeeByPhone(phoneNumber);
            Account acc = new Account(employ.getEmployeeID(), txtUsername.getText(), txtPassword.getText(), Account.level.EMPLOYEE);
            AccountServices as = new AccountServices();
            as.addAcount(acc);
            loadTableData();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateEmployee(ActionEvent e) throws SQLException {

        if (txtName.getText().trim().isEmpty() || txtAddress.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty() || txtPhone.getText().trim().isEmpty() || txtCMND.getText().trim().isEmpty() || txtNationality.getText().trim().isEmpty() || txtUsername.getText().trim().isEmpty()) {
            MessageBox.getBox("Waring", "Vui lòng nhập đầy đủ thông tin khi cập nhật!", Alert.AlertType.WARNING).show();
        } else {
            if (tbEmployee.getSelectionModel().getSelectedItem().getTelephone().equalsIgnoreCase(txtPhone.getText()) && tbEmployee.getSelectionModel().getSelectedItem().getNationalID().equalsIgnoreCase(txtCMND.getText())) {
                if (tbEmployee.getSelectionModel().getSelectedItem() != null) {
                    Employee updatedEmployee = new Employee();
                    String id = tbEmployee.getSelectionModel().getSelectedItem().getEmployeeID();
                    updatedEmployee.setEmployeeID(id);
                    updatedEmployee.setName(txtName.getText());

                    boolean sex;
                    String s = cbSex.getSelectionModel().getSelectedItem();
                    if (s == "Nam") {
                        sex = true;
                    } else {
                        sex = false;
                    }
                    updatedEmployee.setSex(sex);

                    updatedEmployee.setDateOfBirth(dpDateOfBirth.getValue().toString());
                    updatedEmployee.setNationality(txtNationality.getText());
                    updatedEmployee.setNationalID(txtCMND.getText());
                    updatedEmployee.setAddress(txtAddress.getText());
                    updatedEmployee.setEmail(txtEmail.getText());
                    updatedEmployee.setTelephone(txtPhone.getText());
                    updatedEmployee.setPosition(cbPosition.getValue().toString());

                    Account updateAccount = new Account();
                    AccountServices acs = new AccountServices();
                    updateAccount.setAccountID(acs.getAccountByEmployee(id).getAccountID());
                    updateAccount.setEmployeeID(id);
                    updateAccount.setUsername(txtUsername.getText());
                    updateAccount.setPassword(txtPassword.getText());
                    updateAccount.setAccessLevel(Account.level.EMPLOYEE);

                    try {
                        es.updateEmployee(updatedEmployee);
                        acs.updatedAcount(updateAccount);
                        MessageBox.getBox("Success", "Update data completely", Alert.AlertType.INFORMATION).show();
                        loadTableData();
                    } catch (SQLException ex) {
                        MessageBox.getBox("Fail", "Something wrong!", Alert.AlertType.ERROR).show();
                        Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else {
                if (es.checkExitedPhone(txtPhone.getText()) || es.checkExitedCMND(txtCMND.getText())) {
                    MessageBox.getBox("Waring", "Số điện thoại hoặc CMND bị trùng!", Alert.AlertType.WARNING).show();
                } else {

                    if (tbEmployee.getSelectionModel().getSelectedItem() != null) {
                        Employee updatedEmployee = new Employee();
                        String id = tbEmployee.getSelectionModel().getSelectedItem().getEmployeeID();
                        updatedEmployee.setEmployeeID(id);
                        updatedEmployee.setName(txtName.getText());

                        boolean sex;
                        String s = cbSex.getSelectionModel().getSelectedItem();
                        if (s == "Nam") {
                            sex = true;
                        } else {
                            sex = false;
                        }
                        updatedEmployee.setSex(sex);

                        updatedEmployee.setDateOfBirth(dpDateOfBirth.getValue().toString());
                        updatedEmployee.setNationality(txtNationality.getText());
                        updatedEmployee.setNationalID(txtCMND.getText());
                        updatedEmployee.setAddress(txtAddress.getText());
                        updatedEmployee.setEmail(txtEmail.getText());
                        updatedEmployee.setTelephone(txtPhone.getText());
                        updatedEmployee.setPosition(cbPosition.getValue().toString());

                        Account updateAccount = new Account();
                        AccountServices acs = new AccountServices();
                        updateAccount.setAccountID(acs.getAccountByEmployee(id).getAccountID());
                        updateAccount.setEmployeeID(id);
                        updateAccount.setUsername(txtUsername.getText());
                        updateAccount.setPassword(txtPassword.getText());
                        updateAccount.setAccessLevel(Account.level.EMPLOYEE);

                        try {
                            es.updateEmployee(updatedEmployee);
                            acs.updatedAcount(updateAccount);
                            MessageBox.getBox("Success", "Update data completely", Alert.AlertType.INFORMATION).show();
                            loadTableData();
                        } catch (SQLException ex) {
                            MessageBox.getBox("Fail", "Something wrong!", Alert.AlertType.ERROR).show();
                            Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }

        }
    }
    
}
