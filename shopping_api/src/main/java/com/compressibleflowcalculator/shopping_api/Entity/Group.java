package com.compressibleflowcalculator.shopping_api.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "shopping_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    // @JoinColumn(name = "Group_Users", nullable = true)
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Group_User> users;

    @Column(name = "time_joined", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    @Temporal(TemporalType.TIMESTAMP)
    // private Date date_joined;
    private java.util.Date created_at;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Group() {

    }

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public List<Group_User> getUsers() {
        return users;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
