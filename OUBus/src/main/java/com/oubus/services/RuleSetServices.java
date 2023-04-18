/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oubus.services;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author PC
 */
public class RuleSetServices {
    public static int timeCalculator(String dateStart, String aDate){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");    
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = (Date) dateFormat.parse(dateStart);
            d2 = (Date) dateFormat.parse(aDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = d2.getTime() - d1.getTime();

        long diffSeconds = TimeUnit.MILLISECONDS.toSeconds(diff);
        int i = (int) (long) diffSeconds;
        return i;
    }

    public static boolean CheckTime(int timeVal, int timeRes){
//        buy ticket 300 sec
//        book ticket 3600 sec
//        redeem ticket 1800 sec
//        change ticket 3600 sec
//        interaction lock 300 sec
        
        return timeVal>timeRes;
    }

}
