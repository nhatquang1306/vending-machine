package com.techelevator;

import com.techelevator.view.Menu;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
    private static String[] purchaseOptions = {"Feed Money", "Select Product", "Finish Transaction"};
    private static Scanner scanner = new Scanner(System.in);


    private Menu menu;

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() {
        while (true) {
            Inventory inventory = new Inventory();
            ReadingInventory readingInventory = new ReadingInventory();
            File file = new File("vendingmachine.csv");
            readingInventory.addItems(file, inventory);
            double balance = 0.00;


            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                // display vending machine items
                readingInventory.displayItems(file);
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                while (true) {
                    System.out.println("\nCurrent Money Provided: $" + balance);
                    String purchaseChoices = (String) menu.getChoiceFromOptions(purchaseOptions);
                    if (purchaseChoices.equals("Feed Money")) {
                        balance = feedMoney(balance);
                    }
                    // do purchase
                    else if (purchaseChoices.equals("Select Product")) {
                        SelectProduct selectProduct = new SelectProduct();
                        balance = selectProduct.purchase(balance, inventory);

                        //refunding change
                    } else if (purchaseChoices.equals("Finish Transaction")) {
                        LogSales.logGiveChange(balance);
                        balance = balance * 100;
                        int quarters = (int) (balance / 25);
                        balance %= 25;
                        int dimes = (int) (balance / 10);
                        balance %= 10;
                        int nickels = (int) (balance / 5);
                        System.out.println("\nRefunded in " + quarters + " quarter(s)" + (dimes == 0 ? "" : ", " + dimes + " dime(s)") + (nickels == 0 ? "" : ", " + nickels + " nickel(s)") + "\nBalance is now $0.00");
                        System.exit(0);
                    }
                }
            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                System.exit(0);
            }

        }
    }
    public double feedMoney(double balance) {
        System.out.print("\nPlease enter money to purchase in whole dollar amount >>> ");
        String userInput = scanner.nextLine();
        int feedMoney = 0;
        try {
            feedMoney = Integer.parseInt(userInput); //changed this, must be whole number
        } catch (NumberFormatException e) {
            System.out.println(System.lineSeparator() + "*** " + userInput + " is not a valid amount ***");
        }
        balance += feedMoney;
        LogSales.logFeedMoney(feedMoney, balance);
        return balance;
    }


    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
