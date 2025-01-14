package com.backend.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.backend.entity.Customer;
import com.backend.entity.Product;
import com.backend.requestdto.CustomerRequestDTO;
import com.backend.requestdto.ProductRequestDTO;

@Service
public class MappingService {

	private final ModelMapper modelMapper;

	public MappingService() {
		this.modelMapper = new ModelMapper();
	}

	// Converter Customer Entity para RequestDTO
	public CustomerRequestDTO convertToRequestDTO(Customer customer) {
		return modelMapper.map(customer, CustomerRequestDTO.class);
	}

	// Converter Product Entity para RequestDTO
	public ProductRequestDTO convertToRequestDTO(Product product) {
		return modelMapper.map(product, ProductRequestDTO.class);
	}

	// Converter de RequestDTO para Entity
	public Customer convertToEntity(CustomerRequestDTO customerRequestDTO) {
		return modelMapper.map(customerRequestDTO, Customer.class);
	}
}
