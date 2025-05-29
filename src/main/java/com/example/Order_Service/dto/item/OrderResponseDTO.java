package com.example.Order_Service.dto.item;

import java.time.LocalDate;
import java.util.List;

public class OrderResponseDTO {
    private Long id;
    private Long customerId;
    private String customerFirstName;
    private String customerLastName;
    private String customerAddress;

    private String billId; // ID de la facture générée via Bill-Service
    private String status;
    private LocalDate orderDate;
    private double totalPrice;

    private List<OrderItemResponseDTO> items;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getCustomerFirstName() { return customerFirstName; }
    public void setCustomerFirstName(String customerFirstName) { this.customerFirstName = customerFirstName; }

    public String getCustomerLastName() { return customerLastName; }
    public void setCustomerLastName(String customerLastName) { this.customerLastName = customerLastName; }

    public String getCustomerAddress() { return customerAddress; }
    public void setCustomerAddress(String customerAddress) { this.customerAddress = customerAddress; }

    public String getBillId() { return billId; }
    public void setBillId(Long billId) { this.billId = String.valueOf(billId); }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public List<OrderItemResponseDTO> getItems() { return items; }
    public void setItems(List<OrderItemResponseDTO> items) { this.items = items; }
}
