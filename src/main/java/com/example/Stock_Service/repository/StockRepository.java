package com.example.Stock_Service.repository;

import com.example.Stock_Service.model.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<StockModel,Integer> {
    Optional<StockModel> findByProductCode(String productCode);
}
