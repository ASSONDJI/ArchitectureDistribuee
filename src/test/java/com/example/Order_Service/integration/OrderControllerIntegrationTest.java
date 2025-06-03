package com.example.Order_Service.integration;

import com.example.Order_Service.OrderServiceApplication;
import com.example.Order_Service.client.CustomerClient;
import com.example.Order_Service.dto.customer.CustomerDTO;
import com.example.Order_Service.models.Order;
import com.example.Order_Service.repositories.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = OrderServiceApplication.class)
@AutoConfigureMockMvc
public class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository orderRepository;
    @MockBean
    private CustomerClient customerClient;

    @BeforeEach
    public void setUp() {
        Order order1 = new Order();
        order1.setId(1L);
        order1.setCustomerId(100L);
        order1.setOrderDate(LocalDate.now());
        order1.setStatus("EN_ATTENTE");
        order1.setTotalPrice(200.0);

        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1));
    }

    @Test
    void shouldReturnListOfOrders() throws Exception {
        Order order = new Order();
        order.setId(1L);
        order.setCustomerId(100L);
        order.setStatus("EN_ATTENTE");
        order.setOrderDate(LocalDate.now());

        when(orderRepository.findAll()).thenReturn(List.of(order));

        CustomerDTO mockCustomer = new CustomerDTO();
        mockCustomer.setId(100L);
        mockCustomer.setFirstName("Duclair");
        mockCustomer.setLastName("Talotsing");
        mockCustomer.setAddress("123 Rue A");

        when(customerClient.getCustomerById(100L)).thenReturn(mockCustomer);

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
