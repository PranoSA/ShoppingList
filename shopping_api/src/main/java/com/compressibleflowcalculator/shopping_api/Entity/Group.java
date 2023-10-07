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

import org.hibernate.annotations.CreationTimestamp;

import com.compressibleflowcalculator.shopping_api.Views.Groups_User_View;
import com.fasterxml.jackson.annotation.JsonView;
import com.compressibleflowcalculator.shopping_api.Views.Group_View;

@Entity
@Table(name = "shopping_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    @JsonView(Group_View.Group_Response_View.class)
    private UUID id;

    // @JoinColumn(name = "Group_Users", nullable = true)
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    @JsonView(Group_View.Group_Internal_View.class)
    private List<Group_User> users;

    @CreationTimestamp
    @Column(name = "time_joined", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    @Temporal(TemporalType.TIMESTAMP)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Group_View.Group_Response_View.class)
    private java.util.Date date;

    @Column(name = "name")
    @JsonView(Group_View.Group_Request_View.class)
    private String name;

    @Column(name = "description")
    @JsonView(Group_View.Group_Request_View.class)
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

    public java.util.Date getDate() {
        return date;
    }

}
