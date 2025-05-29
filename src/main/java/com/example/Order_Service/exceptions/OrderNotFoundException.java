package com.example.Order_Service.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Long id) {
        super("Commande introuvable avec l'ID : " + id);
    }
}
