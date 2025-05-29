package com.example.Order_Service.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Long id) {
        super("Client introuvable avec l'ID : " + id);
    }
}
