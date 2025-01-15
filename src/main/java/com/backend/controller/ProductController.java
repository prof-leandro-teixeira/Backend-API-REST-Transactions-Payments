package com.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.entity.Product;
import com.backend.requestdto.ProductRequestDTO;
import com.backend.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@Valid @PathVariable Long productId) {
		return ResponseEntity.ok(productService.getProductById(productId));
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
		Product createdProduct = productService.createProduct(productRequestDTO);
		return ResponseEntity.status(201).body(createdProduct);
	}

	@PutMapping("/{productId}")
	public ResponseEntity<Product> updateProduct(@Valid @PathVariable Long productId, @RequestBody ProductRequestDTO productRequestDTO) {
		Product updatedProduct = productService.updateProduct(productId, productRequestDTO);
		return ResponseEntity.ok(updatedProduct);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<Void> deleteProduct(@Valid @PathVariable Long productId) {
		productService.deleteProduct(productId);
		return ResponseEntity.noContent().build();
	}
}
