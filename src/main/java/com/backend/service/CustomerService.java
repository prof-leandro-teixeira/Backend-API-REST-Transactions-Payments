package com.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Customer;
import com.backend.repository.CustomerRepository;
import com.backend.requestdto.CustomerRequestDTO;
import com.backend.utils.ResourceNotFoundException;

import jakarta.transaction.Transactional;

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
				.orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + customerId + " not found."));
	}

	public Customer createCustomer(CustomerRequestDTO customerRequestDTO) {
		Customer customer = new Customer();
		customer.setName(customerRequestDTO.getName());
		customer.setGender(customerRequestDTO.getGender());
		customer.setEmail(customerRequestDTO.getEmail());
		customer.setPhone(customerRequestDTO.getPhone());
		return customerRepository.save(customer);
	}

	public Customer updateCustomer(Long customerId, CustomerRequestDTO customerRequestDTO) {
	    Customer existingCustomer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + customerId + " not found"));
	    existingCustomer.setName(customerRequestDTO.getName());
	    existingCustomer.setEmail(customerRequestDTO.getEmail());
	    existingCustomer.setPhone(customerRequestDTO.getPhone());
	    return customerRepository.save(existingCustomer);
	}

	public void deleteCustomer(Long customerId) {
	    Customer existingCustomer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Product with ID " + customerId + " not found"));
	    customerRepository.delete(existingCustomer);
	}
}
