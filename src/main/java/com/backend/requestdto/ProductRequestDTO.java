package com.backend.requestdto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

	private Long productId;
	private String name;
	private Double price;
	private BigDecimal stock;
}
