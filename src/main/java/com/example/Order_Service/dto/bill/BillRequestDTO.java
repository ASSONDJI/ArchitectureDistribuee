package com.example.Order_Service.dto.bill;

import com.example.Order_Service.dto.product.ProductItemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BillRequestDTO {

    private Long customerId;
    private Long orderId;
    private double amount;
    private List<ProductItemDTO> items;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<ProductItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ProductItemDTO> items) {
        this.items = items;
    }
}
