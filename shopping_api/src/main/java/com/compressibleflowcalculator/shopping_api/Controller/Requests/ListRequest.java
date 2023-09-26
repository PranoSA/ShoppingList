package com.compressibleflowcalculator.shopping_api.Controller.Requests;

import java.time.ZonedDateTime;

public class ListRequest {

    private String name;

    // private ZonedDateTime date;
    private String date;

    public ListRequest() {

    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}
