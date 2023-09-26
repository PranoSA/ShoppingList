package com.compressibleflowcalculator.shopping_api.Controller.Responses.Lists;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException() {
        super("Item Not Found");
    }
}
