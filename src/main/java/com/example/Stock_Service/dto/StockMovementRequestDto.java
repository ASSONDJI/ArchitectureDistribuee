package com.example.Stock_Service.dto;

import com.example.Stock_Service.enums.MovementType;

public record StockMovementRequestDto(
        String productCode,
        int quantity,
        MovementType type) {
}
