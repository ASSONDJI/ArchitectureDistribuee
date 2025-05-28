package com.example.Stock_Service.dto;

import com.example.Stock_Service.enums.MovementType;

import java.util.Date;

public record StockMovementResponseDto(
        int id,
        String productCode,
        int quantity,
        MovementType type,
        Date movementDate) {
}
