package com.backend.requestdto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {
    
    private Long customerId;
    //@NotNull não é necessário já que  @NotEmpty já atende.
    @NotEmpty(message = "O nome é obrigatório.") //Garante que o campo não seja null e não esteja vazio.
    private String name;
    private String gender;
    @NotEmpty(message = "O e-mail é obrigatório.")
    private String email;
    @NotEmpty(message = "O telefone é obrigatório.")
    private String phone;
}
