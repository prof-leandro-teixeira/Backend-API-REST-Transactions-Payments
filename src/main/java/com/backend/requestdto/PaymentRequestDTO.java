package com.backend.requestdto;

import java.time.LocalDate;

import com.backend.entity.Payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {

	private Long paymentId;
	private Long invoiceId; // ID da nota fiscal associada
	private LocalDate paymentDate; // Data do pagamento
	private Double amountPaid; // Valor pago
	private String paymentStatus; // Status do pagamento (PAGO, PENDENTE, etc.)
	private LocalDate dueDate; // Data de vencimento

	public PaymentRequestDTO(Payment payment) {
		this.paymentId = payment.getPaymentId();
		this.invoiceId = payment.getInvoice().getInvoiceId(); // Supondo que Invoice tenha um método getInvoiceId()
		this.amountPaid = payment.getAmountPaid();
		this.paymentStatus = payment.getPaymentStatus();
		this.paymentDate = payment.getPaymentDate();
		this.dueDate = payment.getDueDate();
	}
}
