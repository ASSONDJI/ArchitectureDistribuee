package com.example.Order_Service.services;

import com.example.Order_Service.dto.item.OrderResponseDTO;
import com.example.Order_Service.dto.order.OrderCreateDTO;

import java.util.List;

public interface OrderService {

    OrderResponseDTO createOrder(OrderCreateDTO dto);

    OrderResponseDTO getOrderById(Long id);

    List<OrderResponseDTO> getAllOrders();

    OrderResponseDTO updateOrder(Long id, OrderCreateDTO dto);

    void deleteOrder(Long id);
}
