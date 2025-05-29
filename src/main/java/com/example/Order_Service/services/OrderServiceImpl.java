package com.example.Order_Service.services;

import com.example.Order_Service.client.BillClient;
import com.example.Order_Service.client.CustomerClient;
import com.example.Order_Service.dto.bill.BillRequestDTO;
import com.example.Order_Service.dto.bill.BillResponseDTO;
import com.example.Order_Service.dto.customer.CustomerDTO;
import com.example.Order_Service.dto.item.OrderResponseDTO;
import com.example.Order_Service.dto.order.OrderCreateDTO;
import com.example.Order_Service.exceptions.OrderNotFoundException;
import com.example.Order_Service.mappers.OrderMapper;
import com.example.Order_Service.models.Order;
import com.example.Order_Service.models.OrderItem;
import com.example.Order_Service.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final BillClient billClient;

    public OrderServiceImpl(OrderRepository orderRepository,
                            CustomerClient customerClient,
                            BillClient billClient) {
        this.orderRepository = orderRepository;
        this.customerClient = customerClient;
        this.billClient = billClient;
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> {
                    CustomerDTO customer = customerClient.getCustomerById(order.getCustomerId());
                    return OrderMapper.toResponseDTO(order, customer);
                })
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {
        // Chercher la commande ou lever une exception personnalisée
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        // Appel synchrone au microservice Customer-Service
        CustomerDTO customer = customerClient.getCustomerById(order.getCustomerId());

        // Mapper l'entité Order + le client en réponse DTO enrichie
        return OrderMapper.toResponseDTO(order, customer);
    }

    @Override
    public OrderResponseDTO createOrder(OrderCreateDTO dto) {
        // Étape 1 : Conversion du DTO vers entité
        Order order = OrderMapper.toEntity(dto);

        // Étape 2 : Vérification que le client existe via Customer-Service
        CustomerDTO customer = customerClient.getCustomerById(order.getCustomerId());
        // L’exception sera levée automatiquement dans le client si le client est introuvable

        // Étape 3 : Calcul du prix total
        double totalPrice = order.getItems().stream()
                .mapToDouble(item -> item.getQuantity() * item.getUnitPrice())
                .sum();
        order.setTotalPrice(totalPrice);

        // Étape 4 : Génération de la facture via Bill-Service
        BillRequestDTO billRequest = OrderMapper.toBillRequestDTO(order);
        Long billId = billClient.createBill(billRequest);
        order.setBillId(billId);

        // Étape 5 : Enregistrement de la commande
        orderRepository.save(order);

        // Étape 6 : Transformation vers DTO enrichi avec infos client
        return OrderMapper.toResponseDTO(order, customer);
    }


    @Override
    public OrderResponseDTO updateOrder(Long id, OrderCreateDTO dto) {
        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        existing.getItems().clear();
        for (OrderItem item : OrderMapper.toEntity(dto).getItems()) {
            item.setOrder(existing);
            existing.getItems().add(item);
        }
        existing.setOrderDate(dto.getOrderDate());
        existing.setStatus(dto.getStatus());
        existing.setCustomerId(dto.getCustomerId());

        double totalPrice = existing.getItems().stream()
                .mapToDouble(item -> item.getQuantity() * item.getUnitPrice())
                .sum();
        existing.setTotalPrice(totalPrice);

        orderRepository.save(existing);

        CustomerDTO customer = customerClient.getCustomerById(existing.getCustomerId());
        return OrderMapper.toResponseDTO(existing, customer);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        orderRepository.delete(order);
    }

}
