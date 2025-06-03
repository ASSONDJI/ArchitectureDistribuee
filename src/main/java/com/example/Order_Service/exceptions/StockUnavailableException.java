package com.example.Order_Service.exceptions;

public class StockUnavailableException extends RuntimeException{
    public StockUnavailableException(String message) {
        super(message);
    }

}
