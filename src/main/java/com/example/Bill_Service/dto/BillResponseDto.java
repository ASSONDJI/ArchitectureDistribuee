package com.example.Bill_Service.dto;

import java.util.Date;

public record BillResponseDto(
        Integer  id_bill,
        Date date_bill,
        int quantity_bill,
        double totalPrice_bill,
        String lib_bill)
{

}
