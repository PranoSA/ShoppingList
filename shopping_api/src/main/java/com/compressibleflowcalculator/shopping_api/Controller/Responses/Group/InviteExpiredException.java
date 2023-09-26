package com.compressibleflowcalculator.shopping_api.Controller.Responses.Group;

public class InviteExpiredException extends RuntimeException {
    public InviteExpiredException(String code) {
        super(String.format("No Invite With Code %s Exists", code));
    }
}
