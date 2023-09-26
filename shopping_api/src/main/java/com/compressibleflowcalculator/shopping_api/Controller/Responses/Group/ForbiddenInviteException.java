package com.compressibleflowcalculator.shopping_api.Controller.Responses.Group;

public class ForbiddenInviteException extends RuntimeException {
    public ForbiddenInviteException() {
        super(String.format("You Can Not Send Invites To This Group"));
    }
}
