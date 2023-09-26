package com.compressibleflowcalculator.shopping_api.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compressibleflowcalculator.shopping_api.Entity.Item;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findByListId(UUID listid);
}
