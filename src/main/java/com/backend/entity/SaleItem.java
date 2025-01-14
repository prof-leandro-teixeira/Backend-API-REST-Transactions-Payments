package com.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "sale_item")
public class SaleItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long saleItemId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sale_id", nullable = false)
	@JsonBackReference
	private Sale sale;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id", nullable = false)
	@JsonIgnore
	private Product product;

	@Column(nullable = false)
	private Integer quantity; // Pode ser adicionado @Min(1)

	@Column(nullable = false)
	private Double price; // Pode ser adicionado @Min(0.0)

}
