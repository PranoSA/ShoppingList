package com.compressibleflowcalculator.shopping_api.Entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.compressibleflowcalculator.shopping_api.Views.Groups_User_View;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(indexes = {
        @Index(name = "users_to_group", columnList = "userid")
}, name = "Group_Users")
public class Group_User {
    public static int admin = 0;
    public static int not_admin = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    @JsonView(Groups_User_View.Response.class)
    private UUID id;

    @Column(name = "permissions", nullable = false)
    private int permission;

    @Column(name = "time_joined", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    @Temporal(TemporalType.TIMESTAMP)
    // private Date date_joined;
    private java.util.Date date_joined;

    @Column(name = "invite_code", nullable = true)
    private String invitecode;

    /*
     * @OneToOne()
     * 
     * @Column(name = "userid", nullable = false)
     * private User user;
     */
    @Column(name = "userid", nullable = false)
    private UUID userid;

    public UUID getUserid() {
        return userid;
    }

    public static Group_User Make_Group_Users(Group group, String userid, int permission) {
        Group_User newgGroup_Users = new Group_User();
        // newgGroup_Users.group = group;
        newgGroup_Users.userid = UUID.fromString(userid);
        newgGroup_Users.permission = permission;
        return newgGroup_Users;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupid", nullable = false)
    private Group group;

    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return this.group;
    }

}
