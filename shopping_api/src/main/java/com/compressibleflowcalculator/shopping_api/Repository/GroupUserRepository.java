package com.compressibleflowcalculator.shopping_api.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compressibleflowcalculator.shopping_api.Entity.Group_Users;

@Repository
public interface GroupUserRepository extends JpaRepository<Group_Users, String> {
    List<Group_Users> findAll();

    List<Group_Users> findByUserId(String userId);
}
