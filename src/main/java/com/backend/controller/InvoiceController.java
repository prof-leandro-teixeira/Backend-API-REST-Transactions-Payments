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

import com.backend.entity.Invoice;
import com.backend.requestdto.InvoiceRequestDTO;
import com.backend.service.InvoiceService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	public InvoiceController(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	@GetMapping
	@Transactional
	public ResponseEntity<List<Invoice>> getAllInvoices() {
		return ResponseEntity.ok(invoiceService.getAllInvoices());
	}
	/*
	public ResponseEntity<List<InvoiceRequestDTO>> getAllInvoices() {
		List<Invoice> invoices = invoiceService.getAllInvoice();
		List<InvoiceRequestDTO> invoiceRequestDTOs = invoices.stream()
				.map(invoice -> new InvoiceRequestDTO(invoice.getSale().getSaleId(), null, null))
				.collect(Collectors.toList());
		return ResponseEntity.ok(invoiceRequestDTOs);
	}*/
	
	  @GetMapping("/{invoiceId}")
	    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long invoiceId) {
	        Invoice invoice = invoiceService.getInvoiceById(invoiceId);
	        return ResponseEntity.ok(invoice);
	    }

	@PostMapping
	public ResponseEntity<Invoice> createInvoice(@RequestBody InvoiceRequestDTO invoiceRequestDTO) {
		Invoice createdInvoice = invoiceService.createInvoice(invoiceRequestDTO);
		return ResponseEntity.status(201).body(createdInvoice);
	}

	@PutMapping("/{invoiceId}")
	public ResponseEntity<Invoice> updateInvoice(@PathVariable Long invoiceId,
			@RequestBody InvoiceRequestDTO invoiceRequestDTO) {
		Invoice updatedInvoice = invoiceService.updateInvoice(invoiceId, invoiceRequestDTO);
		return ResponseEntity.ok(updatedInvoice);
	}

	@DeleteMapping("/{invoiceId}")
	public ResponseEntity<Void> deleteInvoice(@PathVariable Long invoiceId) {
		invoiceService.deleteInvoice(invoiceId);
		return ResponseEntity.noContent().build();
	}
}
