package com.compressibleflowcalculator.shopping_api.Entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "listid", nullable = false)
    private ShoppingList list;

    @Column(name = "item_name")
    private String name;

    @Column(name = "creator")
    private UUID owner;

    public UUID getOwner() {
        return owner;
    }

    @JsonProperty("quantity")
    @Column(name = "quantity")
    private int quantity;

    @JsonProperty("cents")
    @Column(name = "cents")
    private int cents;

    public Item() {

    }

    public Item(UUID userid, ShoppingList list, int quantity, int cost, String name) {
        this.owner = userid;// UUID.fromString(userid);
        this.list = list;
        this.quantity = quantity;
        this.cents = cost;
        this.name = name;
    }

}
