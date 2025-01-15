package com.backend.requestdto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

	private Long productId;
	@NotEmpty(message = "O nome do produto é obrigatório.")
	private String name;
	@NotNull(message = "O valor do produto é obrigatório.")
	@DecimalMin(value = "0.0", inclusive = true, message = "O estoque do produto não pode ser negativo.")
	private Double price;
	@NotNull(message = "O estoque do produto é obrigatório.")
	@DecimalMin(value = "0.0", inclusive = true, message = "O estoque do produto não pode ser negativo.")
	private BigDecimal stock;
}
