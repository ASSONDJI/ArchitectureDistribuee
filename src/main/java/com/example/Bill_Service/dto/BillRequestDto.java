package com.example.Bill_Service.dto;

import jakarta.persistence.Column;

import java.util.Date;

public record BillRequestDto(
        int  id_bill,
Date date_bill,
int quantity_bill,
double totalPrice_bill,
String lib_bill) {


}
