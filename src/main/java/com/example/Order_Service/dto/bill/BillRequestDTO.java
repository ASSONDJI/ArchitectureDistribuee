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

}
