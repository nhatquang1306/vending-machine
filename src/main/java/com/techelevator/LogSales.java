package com.techelevator;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogSales {
    //append all sales to log
    public static void logPurchase(String code, String name, double price, double balance) {
        File log = new File("log.txt");
        if (!log.exists()) {
            try {
                log.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            PrintWriter salesLog = new PrintWriter(new FileOutputStream(log.getAbsoluteFile(), true));
            DateFormat date = new SimpleDateFormat();
            Date day = new Date();
            salesLog.append(date.format(day) + " " + name + " " + code + " $" + price + " $" + balance);
            salesLog.println();
            salesLog.flush();
            salesLog.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void logFeedMoney(double moneyFed, double balance) {
        File log = new File("log.txt");
        if (!log.exists()) {
            try {
                log.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            PrintWriter salesLog = new PrintWriter(new FileOutputStream(log.getAbsoluteFile(), true));
            DateFormat date = new SimpleDateFormat();
            Date day = new Date();
            salesLog.append(date.format(day) + " FEED MONEY $" + moneyFed + " $" + balance);
            salesLog.println();
            salesLog.flush();
            salesLog.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void logGiveChange (double balance) {
        File log = new File("log.txt");
        if (!log.exists()) {
            try {
                log.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            PrintWriter salesLog = new PrintWriter(new FileOutputStream(log.getAbsoluteFile(), true));
            DateFormat date = new SimpleDateFormat();
            Date day = new Date();
            salesLog.append(date.format(day) + " GIVE CHANGE $" + balance + " $0.00");
            salesLog.println();
            salesLog.flush();
            salesLog.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

