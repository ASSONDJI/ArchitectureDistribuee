package com.example.Stock_Service.mapper;

import com.example.Stock_Service.dto.StockMovementRequestDto;
import com.example.Stock_Service.dto.StockMovementResponseDto;
import com.example.Stock_Service.model.StockMovementModel;
import org.springframework.stereotype.Component;

@Component
public class StockMovementMapper {

    public StockMovementModel toEntity(StockMovementRequestDto dto) {
        StockMovementModel model = new StockMovementModel();
        model.setProductCode(dto.productCode());
        model.setQuantity(dto.quantity());
        model.setType(dto.type());
        return model;
    }

    public StockMovementResponseDto toDto(StockMovementModel model) {
        return new StockMovementResponseDto(
                model.getId(),
                model.getProductCode(),
                model.getQuantity(),
                model.getType(),
                model.getMovementDate()
        );
    }
}
