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

@Service
public class InvoiceService {

	private final InvoiceRepository invoiceRepository;
	private final SaleRepository saleRepository;

	public List<Invoice> getAllInvoices() {
	    List<Invoice> invoices = invoiceRepository.findAll();
	    
	    // Inicializando as associações preguiçosas
	    for (Invoice invoice : invoices) {
	        Hibernate.initialize(invoice.getSale());
	    }
	    
	    return invoices;
	}
	public List<Invoice> getInvoicesBetweenDates(String startDateStr, String endDateStr) {
		LocalDate startDate = LocalDate.parse(startDateStr);
		LocalDate endDate = LocalDate.parse(endDateStr);
		return invoiceRepository.findByIssueDateBetween(startDate, endDate);
	}

	public InvoiceService(InvoiceRepository invoiceRepository, SaleRepository saleRepository) {
		this.invoiceRepository = invoiceRepository;
		this.saleRepository = saleRepository;
	}

	// Método para criar a nota fiscal
	public Invoice createInvoice(InvoiceRequestDTO invoiceRequestDTO) {
		Optional<Sale> sale = saleRepository.findById(invoiceRequestDTO.getInvoiceId());
		if (!sale.isPresent()) {
			throw new RuntimeException("Sale not found");
		}

		// Criação da fatura (nota fiscal)
		Invoice invoice = new Invoice();
		invoice.setSale(sale.get());
		invoice.setInvoiceNumber(invoiceRequestDTO.getInvoiceNumber());
		invoice.setIssueDate(LocalDate.now()); // Data de emissão é a data atual
		invoice.setTotalAmount(invoiceRequestDTO.getAmount()); // Valor total da fatura

		return invoiceRepository.save(invoice);
	}

	// Método para obter a fatura por ID
	public Invoice getInvoiceById(Long id) {
		return invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found"));
	}

	// Método para atualizar a fatura
	public Invoice updateInvoice(Long id, InvoiceRequestDTO invoiceRequestDTO) {
		// Verifique se a fatura existe
		Invoice existingInvoice = invoiceRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Invoice not found"));

		// Atualiza os campos
		existingInvoice.setInvoiceNumber(invoiceRequestDTO.getInvoiceNumber());
		existingInvoice.setIssueDate(LocalDate.now()); // Atualiza a data de emissão (se necessário)
		existingInvoice.setTotalAmount(invoiceRequestDTO.getAmount());

		// Salva a fatura atualizada
		return invoiceRepository.save(existingInvoice);
	}

	// Método para excluir a fatura
	public void deleteInvoice(Long id) {
		// Verifique se a fatura existe antes de deletar
		Invoice existingInvoice = invoiceRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Invoice not found"));

		invoiceRepository.delete(existingInvoice);
	}
}
