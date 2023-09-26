package com.compressibleflowcalculator.shopping_api.Controller.Responses.Group;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InviteResponse {

    @JsonProperty("group")
    private UUID groupid;

    @JsonProperty("inviteid")
    private UUID inviteid;

    @JsonProperty("code")
    private String code;

    @JsonProperty("expiration")
    private String expires;

    public InviteResponse() {

    }

    public InviteResponse(UUID groupid, UUID inviteid, String code, String expires) {
        this.groupid = groupid;
        this.inviteid = inviteid;
        this.code = code;
        this.expires = expires;
    }

}
