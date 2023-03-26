/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.oubus;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

/**
 *
 * @author bthta
 */
public class BuyTicketsController implements Initializable{
    @FXML
    private ComboBox<String> TimeChoiceBox;
    
    private String[] timeGo ={"3:00","3:30","4:00","4:30","5:00","5:30","6:30","7:00","7:30","8:00","8:30","9:00","9:30","10:00","10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","14:30","15:00","7:00","7:30","8:00","8:30","9:00","9:30","10:00","10:30","11:00","11:30","12:00","12:30"};
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        TimeChoiceBox.getItems().addAll(timeGo);
    }

    
}
