package com.backend.requestdto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleRequestDTO {

	@NotNull
	private Long customerId;

	@NotNull
	private List<SaleItemRequestDTO> saleItems;

	@NotNull
	private Double totalAmount;

	@NotNull
	private LocalDateTime saleDate;
}
