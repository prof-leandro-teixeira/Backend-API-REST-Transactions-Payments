package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.SaleItem;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
	// Métodos 
}
