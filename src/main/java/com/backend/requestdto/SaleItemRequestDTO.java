package com.backend.requestdto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleItemRequestDTO {

	@NotEmpty(message = "O Id da venda é obrigatório.")
	private Long saleId;

	@NotEmpty(message = "O Id do produto é obrigatório.")
	private Long productId;

	@NotEmpty(message = "A quantidade do item é obrigatória.")
	@Min(1)
	private Integer quantity;

	@NotEmpty(message = "O preço do produto é obrigatório.")
	@Min(0)
	private Double price;

}
