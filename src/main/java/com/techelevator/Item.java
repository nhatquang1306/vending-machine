package com.techelevator;

public class Item {
    private String code;
    private String name;
    private double price;
    private String type;
    private int stock;

    public Item(String code, String name, double price, String type) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = 5;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
