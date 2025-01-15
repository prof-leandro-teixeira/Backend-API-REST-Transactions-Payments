package com.backend.requestdto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleRequestDTO {

	@NotEmpty(message = "O campo Id do usuário é obrigatório.")
	private Long customerId;

	@NotEmpty(message = "Um ou mais itens são esperados.")
	private List<SaleItemRequestDTO> saleItems;

	@NotEmpty(message = "O campo total da venda é obrigatório.")
	private Double totalAmount;

	@NotEmpty(message = "A data é obrigatória.")
	private LocalDateTime saleDate;
}
