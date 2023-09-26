package com.compressibleflowcalculator.shopping_api.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compressibleflowcalculator.shopping_api.Entity.Invitation;

@Repository
public interface InviteRepository extends JpaRepository<Invitation, UUID> {

}