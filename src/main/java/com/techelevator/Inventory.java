package com.techelevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    // create an inventory list
    public List<Item> inventoryList = new ArrayList<>();
    public Map<String, Integer> purchaseOptions = new HashMap<>();

    public List<Item> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<Item> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public Map<String, Integer> getPurchaseOptions() {
        return purchaseOptions;
    }

    public void setPurchaseOptions(Map<String, Integer> purchaseOptions) {
        this.purchaseOptions = purchaseOptions;
    }

    public void addItem(Item item) {
        inventoryList.add(item);
    }

    public void addToMap (String code, Integer index) {
        purchaseOptions.put(code, index);
    }

    public void reduceStock (String code) {
        // get current stock
        int currentStock = inventoryList.get(purchaseOptions.get(code)).getStock();
        // reduce stock by 1
        inventoryList.get(purchaseOptions.get(code)).setStock(currentStock -1);
        if (inventoryList.get(purchaseOptions.get(code)).getStock() < 0) {
            inventoryList.get(purchaseOptions.get(code)).setStock(0);
        }

    }


}
