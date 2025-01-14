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

import com.backend.entity.Customer;
import com.backend.requestdto.CustomerRequestDTO;
import com.backend.service.CustomerService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	@Transactional
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
		return ResponseEntity.ok(customerService.getCustomerById(customerId));
	}

	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
		Customer createdCustomer = customerService.createCustomer(customerRequestDTO);
		return ResponseEntity.status(201).body(createdCustomer);
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerRequestDTO customerRequestDTO) {
	    Customer updatedCustomer = customerService.updateCustomer(customerId, customerRequestDTO);
	    return ResponseEntity.ok(updatedCustomer);
	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
	    customerService.deleteCustomer(customerId);
	    return ResponseEntity.noContent().build();
	}
}
