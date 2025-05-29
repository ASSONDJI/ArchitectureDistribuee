package com.example.Order_Service.exceptions;

public class BillCreationException extends RuntimeException{
    public BillCreationException(String message) {
        super("Erreur lors de la création de la facture : " + message);
    }
}
