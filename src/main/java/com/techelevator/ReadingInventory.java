package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadingInventory {
    public void displayItems (File file) {
        System.out.printf("%-5s%-21s%-9s%s", "", "Item", "Price", "Stock");
        System.out.println();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                // read every line of the inventory text file
                String line = scanner.nextLine();
                String[] arr = line.split("\\|");
                System.out.printf("%-5s%-21s%-9s%d", arr[0], arr[1], arr[2], 5);
                System.out.println();
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    public void addItems (File file, Inventory inventory) {
        try (Scanner scanner = new Scanner(file)) {
            int i = 0;
            while (scanner.hasNextLine()) {
                // read every line of the inventory text file
                String line = scanner.nextLine();
                // split the line into each property of the item
                String[] arr = line.split("\\|");
                // construct the item
                Item item = new Item(arr[0], arr[1], Double.parseDouble(arr[2]), arr[3]);
                inventory.addItem(item);
                inventory.addToMap(arr[0], i);
                i++;
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}
