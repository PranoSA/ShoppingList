package com.compressibleflowcalculator.shopping_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compressibleflowcalculator.shopping_api.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
