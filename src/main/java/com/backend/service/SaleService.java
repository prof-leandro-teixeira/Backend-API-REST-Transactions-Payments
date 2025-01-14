package com.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Customer;
import com.backend.entity.Sale;
import com.backend.entity.SaleItem;
import com.backend.repository.CustomerRepository;
import com.backend.repository.ProductRepository;
import com.backend.repository.SaleRepository;
import com.backend.requestdto.SaleRequestDTO;
import com.backend.utils.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductRepository productRepository;

	public SaleService(SaleRepository saleRepository) {
		this.saleRepository = saleRepository;
	}

	public List<Sale> getAllSales() {
		return saleRepository.findAll();
	}

	@Transactional
	public Sale getSaleById(Long saleId) {
		Optional<Sale> sale = saleRepository.findById(saleId);
		return sale.orElseThrow(() -> new ResourceNotFoundException("Sale with ID " + saleId + " not found."));
	}

	@Transactional
	public Sale createSale(SaleRequestDTO saleRequestDTO) {
	    Sale sale = new Sale();
	    Customer customer = customerRepository.findById(saleRequestDTO.getCustomerId())
	        .orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + saleRequestDTO.getCustomerId() + " not found"));
	    sale.setCustomer(customer);
	    List<SaleItem> saleItems = saleRequestDTO.getSaleItems().stream().map(dto -> {
	        SaleItem saleItem = new SaleItem();
	        saleItem.setProduct(productRepository.findById(dto.getProductId())
	            .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + dto.getProductId() + " not found")));
	        saleItem.setQuantity(dto.getQuantity());
	        saleItem.setPrice(dto.getPrice());
	        saleItem.setSale(sale);
	        return saleItem;
	    }).toList();

	    sale.setSaleItems(saleItems);
	    sale.setSaleDate(saleRequestDTO.getSaleDate());
	    sale.setTotalAmount(saleRequestDTO.getTotalAmount());

	    return saleRepository.save(sale);
	}
	
	@Transactional
	public Sale updateSale(Long saleId, SaleRequestDTO saleRequestDTO) {
	    Sale existingSale = saleRepository.findById(saleId)
	        .orElseThrow(() -> new ResourceNotFoundException("Sale with ID " + saleId + " not found"));
	    Customer customer = customerRepository.findById(saleRequestDTO.getCustomerId())
	        .orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + saleRequestDTO.getCustomerId() + " not found"));
	    existingSale.setCustomer(customer);
	    List<SaleItem> saleItems = saleRequestDTO.getSaleItems().stream().map(dto -> {
	        SaleItem saleItem = new SaleItem();
	        saleItem.setProduct(productRepository.findById(dto.getProductId())
	            .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + dto.getProductId() + " not found")));
	        saleItem.setQuantity(dto.getQuantity());
	        saleItem.setPrice(dto.getPrice());
	        saleItem.setSale(existingSale); // Associar o SaleItem à Sale
	        return saleItem;
	    }).toList();

	    existingSale.getSaleItems().clear();
	    existingSale.getSaleItems().addAll(saleItems);

	    existingSale.setSaleDate(saleRequestDTO.getSaleDate());
	    existingSale.setTotalAmount(saleRequestDTO.getTotalAmount());

	    return saleRepository.save(existingSale);
	}

	public void deleteSale(Long saleId) {
	    Sale existingSale = saleRepository.findById(saleId)
	        .orElseThrow(() -> new ResourceNotFoundException("Sale with ID " + saleId + " not found"));
	    saleRepository.delete(existingSale);
	}
}