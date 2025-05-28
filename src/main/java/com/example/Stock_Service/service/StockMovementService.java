package com.example.Stock_Service.service;

import com.example.Stock_Service.dto.StockMovementRequestDto;
import com.example.Stock_Service.dto.StockMovementResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StockMovementService {

    public StockMovementResponseDto save(StockMovementRequestDto request);

    public List<StockMovementResponseDto> getAll();
}
