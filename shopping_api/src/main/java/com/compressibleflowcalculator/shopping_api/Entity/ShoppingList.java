package com.compressibleflowcalculator.shopping_api.Entity;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "lists")
public class ShoppingList {

    @Id
    @JsonProperty("listid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @JsonProperty("time")
    @Column(name = "time_joined", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime date;

    @ManyToOne
    @JsonProperty("group")
    @JoinColumn(name = "groupid")
    private Group group;

    public Group getGroup() {
        return this.group;
    }

    @JsonProperty("creator")
    @Column(name = "creator")
    private UUID creator;

    @JsonProperty("description")
    @Column(name = "description")
    private String description;

    public UUID getCreator() {
        return creator;
    }

    @OneToMany(mappedBy = "list", fetch = FetchType.LAZY)
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public ShoppingList() {

    }

    public ShoppingList(Group group, String name, UUID creator, ZonedDateTime time) {
        this.group = group;
        this.description = name;
        this.creator = creator;
        this.date = time;
    }

}
