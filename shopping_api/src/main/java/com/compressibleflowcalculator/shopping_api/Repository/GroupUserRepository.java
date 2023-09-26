package com.compressibleflowcalculator.shopping_api.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compressibleflowcalculator.shopping_api.Entity.Group_User;

@Repository
public interface GroupUserRepository extends JpaRepository<Group_User, UUID> {
    List<Group_User> findAll();

    List<Group_User> findByUserid(UUID userId);

    List<Group_User> findByUseridAndGroupId(UUID userid, UUID groupid);

}
