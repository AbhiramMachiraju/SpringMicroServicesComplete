package com.abhiram.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhiram.inventoryservice.model.InventoryTO;

public interface InventoryJPARepo extends JpaRepository<InventoryTO, Long> {
}
