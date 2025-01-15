package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.entity.Sale;
import com.backend.requestdto.SaleRequestDTO;
import com.backend.service.SaleService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

	@Autowired
	private SaleService saleService;
	
	public SaleController(SaleService saleService) {
		this.saleService = saleService;
	}

	@GetMapping
	@Transactional
	public ResponseEntity<List<Sale>> getAllSales() {
		return ResponseEntity.ok(saleService.getAllSales());
	}

	@GetMapping("/{saleId}")
	public ResponseEntity<Sale> getSaleById(@Valid @PathVariable Long saleId) {
		return ResponseEntity.ok(saleService.getSaleById(saleId));
	}

	@PostMapping
	public ResponseEntity<Sale> createSale(@Valid @RequestBody SaleRequestDTO saleRequestDTO) {
		Sale createdSale = saleService.createSale(saleRequestDTO);
		return ResponseEntity.status(201).body(createdSale);
	}

	@PutMapping("/{saleId}")
	public ResponseEntity<Sale> updateSale(@Valid @PathVariable Long saleId, @RequestBody SaleRequestDTO saleRequestDTO) {
		Sale updatedSale = saleService.updateSale(saleId, saleRequestDTO);
		return ResponseEntity.ok(updatedSale);
	}

	@DeleteMapping("/{saleId}")
	public ResponseEntity<Void> deleteSale(@Valid @PathVariable Long saleId) {
		saleService.deleteSale(saleId);
		return ResponseEntity.noContent().build();
	}
}
