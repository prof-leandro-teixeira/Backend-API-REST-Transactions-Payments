package com.backend.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice; // Associa o pagamento à nota fiscal

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate; // Data do pagamento

    @Column(nullable = false)
    private Double amountPaid; // Valor pago

    @Column(name = "payment_status", nullable = false)
    private String paymentStatus; // Status do pagamento (ex.: "PAGO", "PENDENTE", etc.)

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate; // Data de vencimento do pagamento
}
