/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.oubus;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 *
 * @author bthta
 */
public class MainController {
    @FXML VBox scense;
    
    public void loadBookTickets(ActionEvent e) throws IOException{
        VBox f = FXMLLoader.load(getClass().getResource("BookTickets.fxml"));
        scense.getChildren().setAll(f);
    }
    
    public void loadBus(ActionEvent e) throws IOException{
        VBox f = FXMLLoader.load(getClass().getResource("Bus.fxml"));
        scense.getChildren().setAll(f);
    }
    
    public void loadEmployee(ActionEvent e) throws IOException{
        VBox f = FXMLLoader.load(getClass().getResource("Employee.fxml"));
        scense.getChildren().setAll(f);
    }
    
    public void loadTrip(ActionEvent e) throws IOException{
        VBox f = FXMLLoader.load(getClass().getResource("Trip.fxml"));
        scense.getChildren().setAll(f);
    }
}
