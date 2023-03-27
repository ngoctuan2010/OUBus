/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.oubus;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

/**
 *
 * @author bthta
 */
public class BookTicketsController {
    @FXML
    private ChoiceBox<String> TimeChoiceBox;
    
    private final String[] timeGo ={"3:00","13:00","9:30"};
    
    public void initialize(URL arg0, ResourceBundle arg1){
        TimeChoiceBox.getItems().addAll(timeGo);
    }
}
