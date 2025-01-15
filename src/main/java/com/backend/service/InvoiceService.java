package com.backend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import com.backend.entity.Invoice;
import com.backend.entity.Sale;
import com.backend.repository.InvoiceRepository;
import com.backend.repository.SaleRepository;
import com.backend.requestdto.InvoiceRequestDTO;
import com.backend.utils.CustomValidationException;
import com.backend.utils.NotAllowedToUpdateInvoiceException;
import com.backend.utils.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class InvoiceService {

	private final InvoiceRepository invoiceRepository;
	private final SaleRepository saleRepository;

	public List<Invoice> getAllInvoices() {
		List<Invoice> invoices = invoiceRepository.findAll();
		for (Invoice invoice : invoices) {
			Hibernate.initialize(invoice.getSale());
		}
		return invoices;
	}

	public InvoiceService(InvoiceRepository invoiceRepository, SaleRepository saleRepository) {
		this.invoiceRepository = invoiceRepository;
		this.saleRepository = saleRepository;
	}

	@Transactional
	public Invoice getInvoiceById(Long invoiceId) {
		Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
		return invoice.orElseThrow(
				() -> new ResourceNotFoundException("Nota fiscal com Id " + invoiceId + " não encontrada."));
	}

	@Transactional
	public List<Invoice> getInvoicesBetweenDates(String startDateStr, String endDateStr) {
		
		if (startDateStr == null || endDateStr == null || startDateStr.isEmpty() || endDateStr.isEmpty()) {
			throw new IllegalArgumentException("As datas de início e fim não podem ser nulas ou vazias.");
		}
		LocalDate startDate = LocalDate.parse(startDateStr);
		LocalDate endDate = LocalDate.parse(endDateStr);
		List<Invoice> invoices = invoiceRepository.findByIssueDateBetween(startDate, endDate);
		if (startDate.isAfter(endDate)) {
		    throw new IllegalArgumentException("A data de início não pode ser maior que a data de fim.");
		}
		if (invoices.isEmpty()) {
			throw new ResourceNotFoundException(
					"Nenhuma nota fiscal encontrada no período de " + startDateStr + " a " + endDateStr + ".");
		}
		return invoices;
	}

	@Transactional
	public Invoice createInvoice(InvoiceRequestDTO invoiceRequestDTO) {
		Optional<Sale> sale = saleRepository.findById(invoiceRequestDTO.getInvoiceId());
		if (!sale.isPresent()) {
			throw new CustomValidationException("Venda não encontrada.");
		}
		Invoice invoice = new Invoice();
		invoice.setSale(sale.get());
		invoice.setInvoiceNumber(invoiceRequestDTO.getInvoiceNumber());
		invoice.setIssueDate(LocalDate.now());
		invoice.setTotalAmount(invoiceRequestDTO.getAmount());
		return invoiceRepository.save(invoice);
	}

	// Uma vez que a nota fiscal foi gerada e registrada, ela representa o final do
	// processo de compra,
	// contendo todas as informações fiscais e tributárias pertinentes à transação.
	// Alterar a nota fiscal após sua emissão pode causar inconsistências e
	// problemas legais,
	// como violação de regulamentações fiscais.
	@Transactional
	public Invoice updateInvoice(Long id, InvoiceRequestDTO invoiceRequestDTO) {
		@SuppressWarnings("unused")
		Invoice existingInvoice = invoiceRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("A nota fiscal não pode ser alterada."));
		throw new NotAllowedToUpdateInvoiceException("Não é permitido alterar uma nota fiscal após sua emissão.");
	}

	public void deleteInvoice(Long id) {
		Invoice existingInvoice = invoiceRepository.findById(id)
				.orElseThrow(() -> new CustomValidationException("A nota fiscal estornada"));

		invoiceRepository.delete(existingInvoice);
	}
}
