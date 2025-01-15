package com.backend.requestdto;

import java.time.LocalDate;

import com.backend.entity.Sale;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceRequestDTO {

	@NotNull(message = "O ID da venda é obrigatório.")
	private Long invoiceId;
	@NotEmpty(message = "A venda é obrigatória.")
    private Sale sale;  
	@NotEmpty(message = "O número da Nota Fiscal é obrigatório.")
    private String invoiceNumber;
	@NotEmpty(message = "A data é obrigatória.")
    private LocalDate issueDate;
	@NotNull(message = "O valor da nota é obrigatório.")
	@DecimalMin(value = "0.0", inclusive = true, message = "O valor da nota não pode ser negativo.")
    private Double amount; 
}
