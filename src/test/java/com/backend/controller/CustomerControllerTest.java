package com.backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.backend.controller.CustomerController;
import com.backend.entity.Customer;
import com.backend.requestdto.CustomerRequestDTO;
import com.backend.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CustomerController.class) // Testa o controlador
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private CustomerRequestDTO customerRequestDTO;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customerRequestDTO = new CustomerRequestDTO();
        customerRequestDTO.setName("John Doe");
        customerRequestDTO.setGender("Male");
        customerRequestDTO.setEmail("johndoe@example.com");
        customerRequestDTO.setPhone("123456789");

        customer = new Customer();
        customer.setCustomerId(1L);
        customer.setName("John Doe");
        customer.setGender("Male");
        customer.setEmail("johndoe@example.com");
        customer.setPhone("123456789");
    }

    @Test
    void testGetAllCustomers() throws Exception {
        // Simula o comportamento do serviço
        when(customerService.getAllCustomers()).thenReturn(List.of(customer));

        mockMvc.perform(get("/api/customers")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].email").value("johndoe@example.com"));
    }

    @Test
    void testGetCustomerById() throws Exception {
        // Simula o comportamento do serviço
        when(customerService.getCustomerById(1L)).thenReturn(customer);

        mockMvc.perform(get("/api/customers/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("johndoe@example.com"));
    }

    @Test
    void testCreateCustomer() throws Exception {
        // Simula o comportamento do serviço
        when(customerService.createCustomer(any(CustomerRequestDTO.class))).thenReturn(customer);

        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(customerRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("johndoe@example.com"));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        // Simula o comportamento do serviço
        when(customerService.updateCustomer(eq(1L), any(CustomerRequestDTO.class))).thenReturn(customer);

        mockMvc.perform(put("/api/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(customerRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("johndoe@example.com"));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        // Simula o comportamento do serviço
        doNothing().when(customerService).deleteCustomer(1L);

        mockMvc.perform(delete("/api/customers/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
