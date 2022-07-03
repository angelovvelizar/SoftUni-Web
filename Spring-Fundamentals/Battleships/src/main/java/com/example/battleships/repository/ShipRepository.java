package com.example.battleships.repository;

import com.example.battleships.model.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long> {

    boolean existsByName(String name);

    @Query("SELECT s FROM ShipEntity s ORDER BY s.health DESC")
    List<ShipEntity> getAllByStatus();
}
