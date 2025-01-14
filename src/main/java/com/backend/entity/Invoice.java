package com.backend.entity; //notafiscal

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long invoiceId; 

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sale_id", nullable = false)
	@JsonIgnore
	private Sale sale; 

	@Column(nullable = false)
	private String invoiceNumber; 

	@Column(name = "issue_date", nullable = false)
	private LocalDate issueDate; 

	@Column(nullable = false)
	private Double totalAmount; 
}
