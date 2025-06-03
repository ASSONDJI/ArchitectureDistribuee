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

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
