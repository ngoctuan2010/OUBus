/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.oubus;

import com.oubus.pojo.Account;
import com.oubus.services.AccountServices;
import com.oubus.utils.MessageBox;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author bthta
 */
public class LoginController {

    AccountServices as = new AccountServices();
    
    @FXML
    TextField txtUsername;
    @FXML
    TextField txtPassword;
    @FXML
    Button btnLogin;

    public void loginHandler(ActionEvent e) throws SQLException, IOException {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            MessageBox.getBox("Blank", "Please enter fully", Alert.AlertType.WARNING).show();
        } else if (as.getUser(username, password) != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent main = (Parent) fxmlLoader.load();
            MainController mc = fxmlLoader.getController();
            mc.initUser(as.getUser(username, password));
            Stage openStage = new Stage();  
            openStage.setScene(new Scene(main));
            Stage closeStage = (Stage) btnLogin.getScene().getWindow();
            closeStage.close();
            openStage.show();
        } else {
            MessageBox.getBox("Wrong", "Username or Password is incorrect", Alert.AlertType.INFORMATION).show();
        }
    }
}
