package com.example.Order_Service.dto.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponseDTO {
    private Long productId;
    private String productName;
    private int quantity;
    private double unitPrice;


}
