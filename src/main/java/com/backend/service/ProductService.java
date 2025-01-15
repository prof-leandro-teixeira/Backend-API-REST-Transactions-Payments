package com.backend.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.backend.entity.Product;
import com.backend.repository.ProductRepository;
import com.backend.requestdto.ProductRequestDTO;
import com.backend.utils.CustomValidationException;
import com.backend.utils.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

	@Autowired
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Transactional
	public Product getProductById(Long productId) {
		Optional<Product> product = productRepository.findById(productId);
		return product
				.orElseThrow(() -> new ResourceNotFoundException("Produto com Id " + productId + " não encontrado"));
	}

	@Transactional
	public List<Product> getProductByName(String name) {
		List<Product> products = productRepository.findByNameContaining(name);
		if (products.isEmpty()) {
			throw new ResourceNotFoundException(
					"Nenhum produto com o nome ou parte do nome '" + name + "' foi encontrado.");
		}
		return products;
	}

	@Transactional
	public Product createProduct(ProductRequestDTO productRequestDTO) {
		if (!StringUtils.hasText(productRequestDTO.getName())) {
			throw new CustomValidationException("O nome do produto é obrigatório.");
		}
		if (productRequestDTO.getPrice() < 0) {
			throw new CustomValidationException("O estoque do produto não pode ser menor que 0.");
		}
		if (productRequestDTO.getStock().compareTo(BigDecimal.ZERO) < 0) {
			throw new CustomValidationException("O estoque do produto não pode ser menor que 0.");
		}
		Product product = new Product();
		product.setName(productRequestDTO.getName());
		product.setPrice(productRequestDTO.getPrice());
		product.setStock(productRequestDTO.getStock());
		return productRepository.save(product);
	}

	@Transactional
	public Product updateProduct(Long productId, ProductRequestDTO productRequestDTO) {
		if (productRequestDTO.getPrice() < 0) {
			throw new CustomValidationException("O estoque do produto não pode ser menor que 0.");
		}
		if (productRequestDTO.getStock().compareTo(BigDecimal.ZERO) < 0) {
			throw new CustomValidationException("O estoque do produto não pode ser menor que 0.");
		}
		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Produto com Id " + productId + " não encontrado"));
		existingProduct.setName(productRequestDTO.getName());
		existingProduct.setPrice(productRequestDTO.getPrice());
		existingProduct.setStock(productRequestDTO.getStock());
		return productRepository.save(existingProduct);
	}

	@Transactional
	public void deleteProduct(Long productId) {
		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Produto com Id " + productId + " não encontrado"));
		productRepository.delete(existingProduct);
	}
}
