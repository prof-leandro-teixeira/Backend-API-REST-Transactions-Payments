package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	// Métodos
}