package com.example.Order_Service.services;

import com.example.Order_Service.client.BillClient;
import com.example.Order_Service.client.CustomerClient;
import com.example.Order_Service.client.ProductClient;
import com.example.Order_Service.dto.customer.CustomerDTO;
import com.example.Order_Service.dto.item.OrderResponseDTO;
import com.example.Order_Service.dto.order.OrderCreateDTO;
import com.example.Order_Service.dto.order.OrderItemCreateDTO;
import com.example.Order_Service.dto.product.ProductDTO;
import com.example.Order_Service.exceptions.OrderNotFoundException;
import com.example.Order_Service.exceptions.StockUnavailableException;
import com.example.Order_Service.models.Order;
import com.example.Order_Service.models.OrderItem;
import com.example.Order_Service.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.Mockito.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;



@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerClient customerClient;

    @Mock
    private BillClient billClient;

    @InjectMocks
    private OrderServiceImpl orderService;

    private OrderCreateDTO dto;
    @Mock
    private ProductClient productClient;

    @BeforeEach
    void setUp() {
        ProductDTO product = new ProductDTO();
        product.setId(1L);
        product.setName("Produit test");
        product.setUnitPrice(10.0);
        product.setStock(20); // Il faut que getStock() existe
        when(productClient.getProductById(1L)).thenReturn(product);

        // Créer une commande factice
        OrderItemCreateDTO item = new OrderItemCreateDTO();
        item.setProductId(1L);
        item.setProductName("Produit Test");
        item.setQuantity(2);
        item.setUnitPrice(10.0);

        List<OrderItemCreateDTO> items = new ArrayList<>();
        items.add(item);

        dto = new OrderCreateDTO();
        dto.setCustomerId(1L);
        dto.setOrderDate(LocalDate.now());
        dto.setStatus("CREATED");
        dto.setItems(items);

        CustomerDTO customer = new CustomerDTO();
        customer.setId(1L);
        customer.setFirstName("Jean");
        customer.setLastName("Dupont");
        customer.setAddress("123 Rue Exemple");

        when(customerClient.getCustomerById(1L)).thenReturn(customer);
        when(billClient.createBill(any())).thenReturn(100L);
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void testCreateOrder() {
        orderService.createOrder(dto);



    }
    @Test
    void testUpdateOrder() {
        // Commande existante avec ID 1
        Order existingOrder = new Order();
        existingOrder.setId(1L);  // ID identique à celui utilisé dans updateOrder
        existingOrder.setCustomerId(1L);
        existingOrder.setStatus("EN_ATTENTE");

        // DTO avec les nouvelles infos
        OrderCreateDTO updatedDTO = new OrderCreateDTO();
        updatedDTO.setCustomerId(1L);
        updatedDTO.setStatus("LIVREE");

        List<OrderItemCreateDTO> items = new ArrayList<>();
        OrderItemCreateDTO item = new OrderItemCreateDTO();
        item.setProductId(10L);
        item.setProductName("Produit A");
        item.setQuantity(2);
        item.setUnitPrice(50.0);
        items.add(item);
        updatedDTO.setItems(items);

        // Mocking
        when(orderRepository.findById(1L)).thenReturn(Optional.of(existingOrder)); // <== IMPORTANT
        when(customerClient.getCustomerById(1L)).thenReturn(new CustomerDTO());
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArgument(0));
        when(billClient.createBill(any())).thenReturn(123L);

        // Appel de la méthode
        OrderResponseDTO result = orderService.updateOrder(1L, updatedDTO);

        // Vérifications
        assertEquals("LIVREE", result.getStatus());
        assertEquals(1, result.getItems().size());
        assertEquals("Produit A", result.getItems().get(0).getProductName());
    }

    @Test
    void testGetOrderById_success() {
        // 1. Simuler une commande
        Order order = new Order();
        order.setId(1L);
        order.setCustomerId(1L);
        order.setOrderDate(LocalDate.now());
        order.setStatus("EN_ATTENTE");
        order.setTotalPrice(150.0);

        // 2. Simuler un client
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setFirstName("Alice");
        customerDTO.setLastName("Durand");
        customerDTO.setAddress("75 avenue des Champs");

        // 3. Simuler les items de la commande
        OrderItem item = new OrderItem();
        item.setProductId(10L);
        item.setProductName("Produit X");
        item.setQuantity(3);
        item.setUnitPrice(50.0);
        order.setItems(List.of(item));

        // 4. Stubbing des mocks
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(customerClient.getCustomerById(1L)).thenReturn(customerDTO);

        // 5. Appel du service
        OrderResponseDTO result = orderService.getOrderById(1L);

        // 6. Vérifications
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Alice", result.getCustomerFirstName());
        assertEquals("EN_ATTENTE", result.getStatus());
        assertEquals(150.0, result.getTotalPrice());
        assertEquals(1, result.getItems().size());
        assertEquals("Produit X", result.getItems().get(0).getProductName());
    }

    @Test
    void testDeleteOrder_success() {
        // ID de la commande à supprimer
        Long orderId = 1L;

        // Création d'une commande fictive existante
        Order order = new Order();
        order.setId(orderId);
        order.setCustomerId(1L);
        order.setStatus("EN_ATTENTE");
        order.setOrderDate(LocalDate.now());

        // Simulation : findById retourne bien la commande
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        // Appel de la méthode à tester
        assertDoesNotThrow(() -> orderService.deleteOrder(orderId));

        // Vérifie que findById et delete (par objet) ont bien été appelés
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    void testDeleteOrder_notFound() {
        Long orderId = 99L;

        // Simuler une commande absente
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Vérifie que l'exception personnalisée est bien levée
        Exception exception = assertThrows(OrderNotFoundException.class, () -> {
            orderService.deleteOrder(orderId);
        });

        // Vérifie que le message correspond
        String message = exception.getMessage();
        System.out.println("Message d'erreur capturé : " + message);
        assertTrue(message.contains("Commande introuvable avec l'ID"),
                "Le message d'erreur devrait mentionner que la commande est introuvable");

        // Vérifie que la méthode delete n’est jamais appelée
        verify(orderRepository, never()).delete(any());
    }
    @Test
    void testGetAllOrders_success() {
        // Création de commandes simulées
        Order order1 = new Order();
        order1.setId(1L);
        order1.setCustomerId(1L);
        order1.setStatus("EN_ATTENTE");
        order1.setOrderDate(LocalDate.now());
        order1.setTotalPrice(150.0);

        Order order2 = new Order();
        order2.setId(2L);
        order2.setCustomerId(2L);
        order2.setStatus("LIVREE");
        order2.setOrderDate(LocalDate.now().minusDays(1));
        order2.setTotalPrice(200.0);

        // Clients simulés
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setId(1L);
        customer1.setFirstName("Jean");
        customer1.setLastName("Dupont");
        customer1.setAddress("Paris");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setId(2L);
        customer2.setFirstName("Marie");
        customer2.setLastName("Durand");
        customer2.setAddress("Lyon");

        // Mocks
        when(orderRepository.findAll()).thenReturn(List.of(order1, order2));
        when(customerClient.getCustomerById(1L)).thenReturn(customer1);
        when(customerClient.getCustomerById(2L)).thenReturn(customer2);

        // Appel de la méthode
        List<OrderResponseDTO> responses = orderService.getAllOrders();

        // Vérifications
        assertEquals(2, responses.size());
        assertEquals("Jean", responses.get(0).getCustomerFirstName());
        assertEquals("Marie", responses.get(1).getCustomerFirstName());

        verify(orderRepository, times(1)).findAll();
        verify(customerClient, times(1)).getCustomerById(1L);
        verify(customerClient, times(1)).getCustomerById(2L);
    }
    @Test
    void testCreateOrder_stockUnavailable_shouldThrowException() {
        // DTO de commande avec un produit
        OrderCreateDTO dto = new OrderCreateDTO();
        dto.setCustomerId(1L);

        OrderItemCreateDTO item = new OrderItemCreateDTO();
        item.setProductId(10L);
        item.setQuantity(10); // Quantité demandée
        item.setUnitPrice(100.0);
        item.setProductName("Produit A");
        dto.setItems(List.of(item));

        // Client existant
        CustomerDTO customer = new CustomerDTO();
        customer.setId(1L);
        customer.setFirstName("Jean");

        // Produit avec stock insuffisant
        ProductDTO product = new ProductDTO();
        product.setId(10L);
        product.setName("Produit A");
        product.setUnitPrice(100.0);
        product.setStock(5); // Stock disponible < 10

        when(customerClient.getCustomerById(1L)).thenReturn(customer);
        when(productClient.getProductById(10L)).thenReturn(product);

        // Vérification que l'exception est levée
        StockUnavailableException exception = assertThrows(StockUnavailableException.class, () -> {
            orderService.createOrder(dto);
        });

        // Vérifie que le message contient des infos sur le produit
        assertTrue(exception.getMessage().contains("Stock insuffisant"));
        assertTrue(exception.getMessage().contains("Produit A"));
    }



}
