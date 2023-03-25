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
    
    private String[] timeGo ={"3:00","3:30","4:00","4:30","5:00","13:00","9:30"};
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        TimeChoiceBox.getItems().addAll(timeGo);
    }

    
}
