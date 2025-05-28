package com.example.Stock_Service.model;

import com.example.Stock_Service.enums.MovementType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StockMovementModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "PRODUCTCODE")
    private String productCode;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "MOVEMENTDATE")
    private Date movementDate;

    @Enumerated(EnumType.STRING)
    private MovementType type;
}
