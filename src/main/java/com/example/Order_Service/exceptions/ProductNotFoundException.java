package com.example.Order_Service.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id) {
        super("Produit introuvable avec l'ID : " + id);
    }
}
