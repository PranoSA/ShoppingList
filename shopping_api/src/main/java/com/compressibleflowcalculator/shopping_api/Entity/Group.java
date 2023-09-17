package com.compressibleflowcalculator.shopping_api.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.UUID;

@Entity
@Table(name = "shopping_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "Group_Users", nullable = true)
    private User[] users;

    @Column(name = "time_joined", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    @Temporal(TemporalType.TIMESTAMP)
    // private Date date_joined;
    private java.util.Date created_at;

    @Column(name = "name")
    private String name;

    public Group() {

    }

    public Group(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
