package com.backend.requestdto;

import java.time.LocalDate;

import com.backend.entity.Sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceRequestDTO {

	private Long invoiceId;
    private Sale sale;  
    private String invoiceNumber;
    private LocalDate issueDate;
    private Double amount; 
}
