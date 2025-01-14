package com.backend.requestdto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleItemRequestDTO {

	
	@NotNull
	private Long saleId;

	@NotNull
	private Long productId;

	@NotNull
	@Min(1)
	private Integer quantity;

	@NotNull
	@Min(0)
	private Double price;

}
