package com.backend.requestdto;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolationException;

public class CustomerRequestDTOTest {

    @Test
    void testValidation() {
        CustomerRequestDTO dto = new CustomerRequestDTO();
        dto.setName(null); // Falta o nome

        // Simula a validação de anotações como @NotNull
        assertThrows(ConstraintViolationException.class, () -> {
            // Aqui você validaria com um validator real, usando algo como javax.validation
        });
    }
}
