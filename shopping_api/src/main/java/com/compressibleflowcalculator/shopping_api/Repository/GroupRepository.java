package com.compressibleflowcalculator.shopping_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compressibleflowcalculator.shopping_api.Entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, String> {

}
