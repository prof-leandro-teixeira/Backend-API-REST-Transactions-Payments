package com.backend.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {
    
    private Long customerId;
    private String name;
    private String gender;
    private String email;
    private String phone;
}
