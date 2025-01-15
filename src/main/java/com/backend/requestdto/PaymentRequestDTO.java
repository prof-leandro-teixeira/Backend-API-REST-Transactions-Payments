package com.backend.requestdto;

import java.time.LocalDate;

import com.backend.entity.Payment;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {

	private Long paymentId;
	@NotEmpty(message = "O ID da nota fiscal associada é obrigatório.")
	private Long invoiceId;
	@NotEmpty(message = "A data do pagamento é obrigatória.")
	private LocalDate paymentDate; 
	@NotEmpty(message = "O valor total é obrigatório.")
	private Double amountPaid; 
	@NotEmpty(message = "O Status do pagamento (PAGO, PENDENTE, etc.) é obrigatório.")
	private String paymentStatus; // 
	@NotEmpty(message = "A data de vencimento é obrigatória.")
	private LocalDate dueDate; 

	public PaymentRequestDTO(Payment payment) {
		this.paymentId = payment.getPaymentId();
		this.invoiceId = payment.getInvoice().getInvoiceId(); // Invoice tem um método getInvoiceId()
		this.amountPaid = payment.getAmountPaid();
		this.paymentStatus = payment.getPaymentStatus();
		this.paymentDate = payment.getPaymentDate();
		this.dueDate = payment.getDueDate();
	}
}
