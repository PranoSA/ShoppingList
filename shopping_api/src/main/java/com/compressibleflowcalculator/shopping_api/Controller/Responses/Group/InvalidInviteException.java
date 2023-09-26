package com.compressibleflowcalculator.shopping_api.Controller.Responses.Group;

public class InvalidInviteException extends RuntimeException {
    public InvalidInviteException(String code) {
        super(String.format("No Invite With Code %s Exists", code));
    }
}
