package com.example.Order_Service.mappers;

import com.example.Order_Service.dto.bill.BillRequestDTO;
import com.example.Order_Service.dto.customer.CustomerDTO;
import com.example.Order_Service.dto.item.OrderItemResponseDTO;
import com.example.Order_Service.dto.item.OrderResponseDTO;
import com.example.Order_Service.dto.order.OrderCreateDTO;
import com.example.Order_Service.dto.product.ProductItemDTO;
import com.example.Order_Service.models.Order;
import com.example.Order_Service.models.OrderItem;


import java.util.List;
import java.util.stream.Collectors;

/**
 * ✅ OrderMapper
 * Classe utilitaire pour convertir les entités Order DTOs.
 */
public class OrderMapper {

    /**
     * 🔁 Convertit un DTO de création de commande vers l'entité Order.
     *
     * @param dto données de la requête POST
     * @return Order prêt à être persisté
     */
    public static Order toEntity(OrderCreateDTO dto) {
        Order order = new Order();
        order.setCustomerId(dto.getCustomerId());
        order.setOrderDate(dto.getOrderDate());
        order.setStatus(dto.getStatus());

        // Crée les OrderItems associés
        List<OrderItem> items = dto.getItems().stream().map(itemDTO -> {
            OrderItem item = new OrderItem();
            item.setProductId(itemDTO.getProductId());
            item.setProductName(itemDTO.getProductName());
            item.setQuantity(itemDTO.getQuantity());
            item.setUnitPrice(itemDTO.getUnitPrice());
            item.setOrder(order); // rattachement bidirectionnel
            return item;
        }).collect(Collectors.toList());

        order.setItems(items);
        return order;
    }

    /**
     * 🔁 Convertit une entité Order + CustomerDTO en OrderResponseDTO
     *
     * @param order    entité persistée
     * @param customer infos client depuis Customer-Service
     * @return DTO pour réponse API
     */
    public static OrderResponseDTO toResponseDTO(Order order, CustomerDTO customer) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setBillId(order.getBillId());
        dto.setCustomerId(order.getCustomerId());

        if (customer != null) {
            dto.setCustomerFirstName(customer.getFirstName());
            dto.setCustomerLastName(customer.getLastName());
            dto.setCustomerAddress(customer.getAddress());
        }

        List<OrderItemResponseDTO> itemDTOs = order.getItems().stream().map(item -> {
            OrderItemResponseDTO dtoItem = new OrderItemResponseDTO();
            dtoItem.setProductId(item.getProductId());
            dtoItem.setProductName(item.getProductName());
            dtoItem.setQuantity(item.getQuantity());
            dtoItem.setUnitPrice(item.getUnitPrice());
            return dtoItem;
        }).collect(Collectors.toList());

        dto.setItems(itemDTOs);
        return dto;
    }

    public static BillRequestDTO toBillRequestDTO(Order order) {
        BillRequestDTO bill = new BillRequestDTO();
        bill.setOrderId(order.getId()); // ⚠️ peut être null avant la sauvegarde
        bill.setCustomerId(order.getCustomerId());
        bill.setAmount(order.getTotalPrice());

        List<ProductItemDTO> items = order.getItems().stream().map(item -> {
            ProductItemDTO p = new ProductItemDTO();
            p.setProductId(item.getProductId());
            p.setName(item.getProductName());
            p.setQuantity(item.getQuantity());
            p.setUnitPrice(item.getUnitPrice());
            return p;
        }).toList();

        bill.setItems(items);
        return bill;
    }

}
