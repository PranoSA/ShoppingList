package com.compressibleflowcalculator.shopping_api.Controller.Requests;

import java.time.ZonedDateTime;

public class GroupRequest {
    private String name;
    private String desciption;

    public GroupRequest() {

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return desciption;
    }

}
