package com.compressibleflowcalculator.shopping_api.Controller.Requests;

public class ItemPostRequest {
    private int quantity;

    private int price;

    private String name;

    public ItemPostRequest() {

    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

}
