package com.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.backend.entity.Invoice;
import com.backend.entity.Payment;
import com.backend.repository.InvoiceRepository;
import com.backend.repository.PaymentRepository;
import com.backend.requestdto.PaymentRequestDTO;

@Service
public class PaymentService {

	private final PaymentRepository paymentRepository;
	private final InvoiceRepository invoiceRepository;

	public PaymentService(PaymentRepository paymentRepository, InvoiceRepository invoiceRepository) {
		this.paymentRepository = paymentRepository;
		this.invoiceRepository = invoiceRepository;
	}

	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	public Payment createPayment(PaymentRequestDTO paymentRequestDTO) {
		Optional<Invoice> invoice = invoiceRepository.findById(paymentRequestDTO.getInvoiceId());
		if (!invoice.isPresent()) {
			throw new RuntimeException("Invoice not found");
		}

		Payment payment = new Payment();
		payment.setInvoice(invoice.get());
		payment.setPaymentDate(paymentRequestDTO.getPaymentDate());
		payment.setAmountPaid(paymentRequestDTO.getAmountPaid());
		payment.setPaymentStatus(paymentRequestDTO.getPaymentStatus());
		payment.setDueDate(paymentRequestDTO.getDueDate());

		// Salvar pagamento
		return paymentRepository.save(payment);
	}

	public Payment getPaymentById(Long id) {
		return paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
	}

	public Payment updatePayment(Long id, PaymentRequestDTO paymentRequestDTO) {
		Payment existingPayment = paymentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Payment not found"));

		existingPayment.setPaymentDate(paymentRequestDTO.getPaymentDate());
		existingPayment.setAmountPaid(paymentRequestDTO.getAmountPaid());
		existingPayment.setPaymentStatus(paymentRequestDTO.getPaymentStatus());
		existingPayment.setDueDate(paymentRequestDTO.getDueDate());

		return paymentRepository.save(existingPayment);
	}

	public void deletePayment(Long id) {
		Payment existingPayment = paymentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Payment not found"));

		paymentRepository.delete(existingPayment);
	}

}
