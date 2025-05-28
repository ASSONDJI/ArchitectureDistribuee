package com.example.Stock_Service.repository;

import com.example.Stock_Service.model.StockMovementModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovementModel,Integer> {
}
