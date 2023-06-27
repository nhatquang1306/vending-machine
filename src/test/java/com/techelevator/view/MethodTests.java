package com.techelevator.view;

import com.techelevator.Inventory;
import com.techelevator.ReadingInventory;
import com.techelevator.SelectProduct;
import com.techelevator.VendingMachineCLI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.RandomAccessFile;


public class MethodTests {

    Inventory inventory;
    ReadingInventory readingInventory;
    File file;

    @Before
    public void setup() {
        readingInventory = new ReadingInventory();
        inventory = new Inventory();
        file = new File("vendingmachine.csv");
        readingInventory.addItems(file, inventory);

    }
    @Test
    public void test_if_items_from_file_are_added_to_list_and_map() {

        // actual
        boolean containsA1 = inventory.getPurchaseOptions().containsKey("A1");
        boolean doesntContainD5 = inventory.getPurchaseOptions().containsKey("D5");
        boolean listContainsWonkaBar = inventory.getInventoryList().get(6).getName().equals("Wonka Bar");
        boolean testStock = inventory.getInventoryList().get(7).getStock() == 5;
        // assert
        Assert.assertTrue(containsA1);
        Assert.assertFalse(doesntContainD5);
        Assert.assertTrue(listContainsWonkaBar);
        Assert.assertTrue(testStock);
    }
    @Test
    public void test_if_stock_is_reduced_after_purchased() {
        inventory.reduceStock("A1");
        // actual
        int newStockA1 = inventory.getInventoryList().get(0).getStock();
        // assert
        Assert.assertEquals(4, newStockA1);
        inventory.reduceStock("A1");
        inventory.reduceStock("A1");
        inventory.reduceStock("A1");
        inventory.reduceStock("A1");
        inventory.reduceStock("A1");
        // actual
        int outOfStock = inventory.getInventoryList().get(0).getStock();
        // assert
        Assert.assertEquals(0, outOfStock);


    }
}
