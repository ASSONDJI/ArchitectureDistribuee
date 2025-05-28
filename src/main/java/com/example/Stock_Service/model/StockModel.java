package com.example.Stock_Service.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StockModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String productCode;

    @Column(name = "QUANTITY")
    private int quantity; // Quantité actuelle disponible en stock
}
