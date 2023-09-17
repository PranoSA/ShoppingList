package com.compressibleflowcalculator.shopping_api.Entity;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "shopping_users")
public class User {
    /*
     * @Id
     * 
     * @GeneratedValue(strategy = GenerationType.AUTO)
     * 
     * @Column(name = "id", nullable = false, columnDefinition =
     * "UUID default gen_random_uuid()")
     * private UUID id;
     */

    @Id
    @Column(name = "id", nullable = false, columnDefinition = "VARCHAR(256)")
    private String id;

    @Column(name = "time_joined", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date_joined;

}
