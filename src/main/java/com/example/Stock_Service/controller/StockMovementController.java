package com.example.Stock_Service.controller;

import com.example.Stock_Service.dto.StockMovementRequestDto;
import com.example.Stock_Service.dto.StockMovementResponseDto;
import com.example.Stock_Service.service.StockMovementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-movements")
public class StockMovementController {

    private final StockMovementService service;

    public StockMovementController(StockMovementService service) {
        this.service = service;
    }

    @PostMapping
    public StockMovementResponseDto createMovement(@RequestBody StockMovementRequestDto request) {
        return service.save(request);
    }

    @GetMapping
    public List<StockMovementResponseDto> getAllMovements() {
        return service.getAll();
    }
}
