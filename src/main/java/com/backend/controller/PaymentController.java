package com.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.backend.entity.Payment;
import com.backend.requestdto.PaymentRequestDTO;
import com.backend.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@PostMapping
	public ResponseEntity<Payment> createPayment(@Valid @RequestBody PaymentRequestDTO paymentRequestDTO) {
		Payment createdPayment = paymentService.createPayment(paymentRequestDTO);
		return ResponseEntity.status(201).body(createdPayment);
	}

	@GetMapping
	public ResponseEntity<List<PaymentRequestDTO>> getAllPayments() {
		List<Payment> payments = paymentService.getAllPayments(); // Chamando o método correto
		List<PaymentRequestDTO> paymentRequestDTOs = payments.stream() // Alterado de 'payment' para 'payments'
				.map(payment -> new PaymentRequestDTO(payment)) // Ajuste no mapeamento
				.collect(Collectors.toList());
		return ResponseEntity.ok(paymentRequestDTOs);
	}

	@GetMapping("/{paymentId}")
	public ResponseEntity<Payment> getPaymentById(@Valid @PathVariable Long paymentId) {
		Payment payment = paymentService.getPaymentById(paymentId);
		return ResponseEntity.ok(payment);
	}

	@PutMapping("/{paymentId}")
	public ResponseEntity<Payment> updatePayment(@Valid @PathVariable Long paymentId,
			@RequestBody PaymentRequestDTO paymentRequestDTO) {
		Payment updatedPayment = paymentService.updatePayment(paymentId, paymentRequestDTO);
		return ResponseEntity.ok(updatedPayment);
	}

	@DeleteMapping("/{paymentId}")
	public ResponseEntity<Void> deletePayment(@Valid @PathVariable Long paymentId) {
		paymentService.deletePayment(paymentId);
		return ResponseEntity.noContent().build();
	}
}
