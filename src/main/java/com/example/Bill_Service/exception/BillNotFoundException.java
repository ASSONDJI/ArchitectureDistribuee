package com.example.Bill_Service.exception;

public class BillNotFoundException extends RuntimeException {
    public BillNotFoundException(Integer BillId) {
        super("Bill Not Found"+BillId);
    }
}
