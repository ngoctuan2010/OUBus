/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.utils;

import javafx.scene.control.Alert;

/**
 *
 * @author PC
 */
public class MessageBox {
<<<<<<< HEAD
    public static Alert getBox(String title, String content, Alert.AlertType type){
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setContentText(content);
        
        return a;
        
=======
    public static Alert getBox(String title, String content, Alert.AlertType type) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setContentText(content);
        return a;
>>>>>>> 6158e669438e441fd7fdabca48cf5ef30034edac
    }
}
