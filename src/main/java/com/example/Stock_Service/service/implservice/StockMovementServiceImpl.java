package com.example.Stock_Service.service.implservice;

import com.example.Stock_Service.dto.StockMovementRequestDto;
import com.example.Stock_Service.dto.StockMovementResponseDto;
import com.example.Stock_Service.enums.MovementType;
import com.example.Stock_Service.mapper.StockMovementMapper;
import com.example.Stock_Service.model.StockModel;
import com.example.Stock_Service.model.StockMovementModel;
import com.example.Stock_Service.repository.StockMovementRepository;
import com.example.Stock_Service.repository.StockRepository;
import com.example.Stock_Service.service.StockMovementService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StockMovementServiceImpl implements StockMovementService {

    private final StockMovementRepository movementRepository;
    private final StockRepository stockRepository;
    private final StockMovementMapper mapper;

    public StockMovementServiceImpl(StockMovementRepository movementRepository, StockRepository stockRepository, StockMovementMapper mapper) {
        this.movementRepository = movementRepository;
        this.stockRepository = stockRepository;
        this.mapper = mapper;
    }


    @Override
    public StockMovementResponseDto save(StockMovementRequestDto request) {
        // Récupère ou crée un stock pour ce produit
        StockModel stock = stockRepository.findByProductCode(request.productCode())
                .orElseGet(() -> new StockModel(0, request.productCode(), 0));

        // Logique de gestion IN / OUT
        if (request.type() == MovementType.OUT) {
            if (stock.getQuantity() < request.quantity()) {
                throw new RuntimeException("Stock insuffisant pour le produit : " + request.productCode());
            }
            stock.setQuantity(stock.getQuantity() - request.quantity());
        } else if (request.type() == MovementType.IN) {
            stock.setQuantity(stock.getQuantity() + request.quantity());
        }

        stockRepository.save(stock); // Mise à jour du stock

        StockMovementModel movement = mapper.toEntity(request);
        movement.setMovementDate(new Date());
        return mapper.toDto(movementRepository.save(movement));
    }

    @Override
    public List<StockMovementResponseDto> getAll() {
        return movementRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }
}
