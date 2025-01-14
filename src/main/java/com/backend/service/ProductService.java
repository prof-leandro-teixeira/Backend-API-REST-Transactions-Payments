package com.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.backend.entity.Product;
import com.backend.repository.ProductRepository;
import com.backend.requestdto.ProductRequestDTO;
import com.backend.utils.ResourceNotFoundException;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(Long productId) {
		Optional<Product> product = productRepository.findById(productId);
		return product.orElseThrow(() -> new ResourceNotFoundException("Product with ID " + productId + " not found"));
	}

	public Product createProduct(ProductRequestDTO productRequestDTO) {
		Product product = new Product();
		product.setName(productRequestDTO.getName());
		product.setPrice(productRequestDTO.getPrice());
		product.setStock(productRequestDTO.getStock());
		return productRepository.save(product);
	}

	public Product updateProduct(Long productId, ProductRequestDTO productRequestDTO) {
		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product with ID " + productId + " not found"));
		existingProduct.setName(productRequestDTO.getName());
		existingProduct.setPrice(productRequestDTO.getPrice());
		existingProduct.setStock(productRequestDTO.getStock());
		return productRepository.save(existingProduct);
	}

	public void deleteProduct(Long productId) {
		Product existingProduct = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with ID " + productId + " not found"));
		productRepository.delete(existingProduct);
	}
}
