package com.compressibleflowcalculator.shopping_api.Entity;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(indexes = {
        @Index(name = "users_to_group", columnList = "userid")
}, name = "Group_Users")
public class Group_Users {
    public static int admin = 0;
    public static int not_admin = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
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
    @Column(name = "userid", columnDefinition = "VARCHAR(128)", nullable = false)
    private String userid;

    public String getUserId() {
        return userid;
    }

    @OneToOne()
    // @Column(name = "groupid", nullable = false)
    private Group group;

}
