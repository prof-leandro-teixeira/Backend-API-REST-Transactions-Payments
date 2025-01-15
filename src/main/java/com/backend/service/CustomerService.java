package com.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.backend.entity.Customer;
import com.backend.repository.CustomerRepository;
import com.backend.requestdto.CustomerRequestDTO;
import com.backend.utils.CustomValidationException;
import com.backend.utils.ResourceNotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;

@Service
public class CustomerService {

	@Autowired
	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Transactional
	public Customer getCustomerById(Long customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		return customer
				.orElseThrow(() -> new ResourceNotFoundException("Cliente com ID " + customerId + " não encontrado."));
	}
	
	@Transactional
	public List<Customer> getCustomersByName(String name) {
	    List<Customer> customers = customerRepository.findByNameContaining(name);
	    if (customers.isEmpty()) {
	        throw new ResourceNotFoundException("Nenhum cliente com o nome ou parte do nome '" + name + "' foi encontrado.");
	    }
	    return customers;
	}

	@Transactional
	public Customer createCustomer(CustomerRequestDTO customerRequestDTO) {
		if (!StringUtils.hasText(customerRequestDTO.getName())) {
			throw new CustomValidationException("O nome do cliente é obrigatório.");
		}
		if (customerRepository.existsByEmail(customerRequestDTO.getEmail())) {
			throw new CustomValidationException("Já existe um cliente com esse e-mail.");
		}
		if (!StringUtils.hasText(customerRequestDTO.getPhone())) {
			throw new CustomValidationException("O telefone do cliente é obrigatório.");
		}
		Customer customer = new Customer();
		customer.setName(customerRequestDTO.getName());
		customer.setGender(customerRequestDTO.getGender());
		customer.setEmail(customerRequestDTO.getEmail());
		customer.setPhone(customerRequestDTO.getPhone());
		return customerRepository.save(customer);
	}

	@Transactional
	public Customer updateCustomer(Long customerId, CustomerRequestDTO customerRequestDTO) {
		if (!StringUtils.hasText(customerRequestDTO.getName())) {
			throw new CustomValidationException("O nome do cliente é obrigatório.");
		}
		if (customerRepository.existsByEmailAndIdNot(customerRequestDTO.getEmail(), customerId)) {
			throw new ValidationException("Já existe um cliente com esse e-mail.");
		}
		Customer existingCustomer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + customerId + " not found"));
		existingCustomer.setName(customerRequestDTO.getName());
		existingCustomer.setGender(customerRequestDTO.getGender());
		existingCustomer.setEmail(customerRequestDTO.getEmail());
		existingCustomer.setPhone(customerRequestDTO.getPhone());

		return customerRepository.save(existingCustomer);
	}

	@Transactional
	public void deleteCustomer(Long customerId) {
		Customer existingCustomer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + customerId + " not found"));
		customerRepository.delete(existingCustomer);
	}
}
