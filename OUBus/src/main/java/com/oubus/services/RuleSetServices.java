/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.services;

/**
 *
 * @author PC
 */
public class RuleSetServices {
    
    public static boolean CheckTime(int timeVal, int timeRes){
//        buy ticket 300 sec (Thịnh)
//        book ticket 3600 sec (Thịnh)
//        redeem ticket 1800 sec (Tâm)
//        change ticket 3600 sec (Tâm)
//        interaction lock 300 sec (Tuấn)
        
        return timeVal>timeRes;
    }

}
