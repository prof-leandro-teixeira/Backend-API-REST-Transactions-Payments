package com.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByNameContaining(String partialName);
	List<Customer> findByGender(String gender);
	Optional<Customer> findByEmail(String email);
	List<Customer> findByPhone(String phone);
	boolean existsByEmail(String email);
	boolean existsByEmailAndIdNot(String email, Long customerId);
}
