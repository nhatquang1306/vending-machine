package com.techelevator;

import java.text.DecimalFormat;
import java.util.Scanner;

public class SelectProduct {
    public double purchase(double balance, Inventory inventory) {
        DecimalFormat df = new DecimalFormat("0.00");
        for (Item i : inventory.getInventoryList()) {
            System.out.printf("%s) %s", i.getCode(), i.getName());
            System.out.println();
        }
        System.out.print("\nPlease input a code to purchase >>> ");
        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.nextLine().toUpperCase();
        if (!inventory.getPurchaseOptions().containsKey(userChoice)) {
            System.out.println("\nThe product code doesn't exist");
            return balance;
        }
        int index = inventory.getPurchaseOptions().get(userChoice);

        if (inventory.getInventoryList().get(index).getStock() > 0 && balance >= inventory.getInventoryList().get(index).getPrice()) {
            inventory.reduceStock(userChoice); //reduced stock based on user choice
            balance -= inventory.getInventoryList().get(index).getPrice(); //reduced balance according to price
            balance = Double.parseDouble(df.format(balance));
            String message = "";
            switch (inventory.getInventoryList().get(index).getType()) {
                case "Chip":
                    message = "\nCrunch Crunch, Yum!";
                case "Candy":
                    message = "\nMunch Munch, Yum!";
                case "Drink":
                    message = "\nGlug Glug, Yum!";
                case "Gum":
                    message = "\nChew Chew, Yum!";
            }
            System.out.println(message);
            LogSales.logPurchase(userChoice.toUpperCase(), inventory.getInventoryList().get(index).getName(),
                    inventory.getInventoryList().get(index).getPrice(), balance);

            return balance;
        }

        if (inventory.getInventoryList().get(index).getStock() == 0) {
            System.out.println();
            System.out.println("\nThe product is sold out");
            return balance;
        }

        if (balance < inventory.getInventoryList().get(index).getPrice()) {
            System.out.println("\nBalance is less than cost of product(s) selected, please add more money");
            return balance;
        }
        return balance;
    }

}
