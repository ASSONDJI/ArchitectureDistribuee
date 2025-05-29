package com.example.Order_Service.dto.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class OrderCreateDTO {

    @NotNull(message = "L'identifiant du client est obligatoire")
    private Long customerId;

    @NotNull(message = "La date de la commande est obligatoire")
    private LocalDate orderDate;

    @NotBlank(message = "Le statut de la commande est obligatoire")
    private String status;

    @Valid
    @NotNull(message = "La liste des produits est obligatoire")
    private List<OrderItemCreateDTO> items;

    // Getters & Setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItemCreateDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemCreateDTO> items) {
        this.items = items;
    }
}
