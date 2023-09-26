package com.compressibleflowcalculator.shopping_api.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compressibleflowcalculator.shopping_api.Entity.Group;
import com.compressibleflowcalculator.shopping_api.Entity.ShoppingList;
import java.util.List;

public interface ListRepository extends JpaRepository<ShoppingList, UUID> {
    List<ShoppingList> findByGroup(Group group);

    List<ShoppingList> findByGroupId(UUID groupid);
}
