package com.example.Order_Service.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderItemCreateDTO {

    @NotNull(message = "L'ID du produit est requis")
    private Long productId;

    @NotBlank(message = "Le nom du produit est requis")
    private String productName;

    @Min(value = 1, message = "La quantité doit être au moins 1")
    private int quantity;

    @Min(value = 0, message = "Le prix unitaire doit être positif")
    private double unitPrice;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
