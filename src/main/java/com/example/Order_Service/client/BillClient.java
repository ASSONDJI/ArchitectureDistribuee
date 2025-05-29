package com.example.Order_Service.client;

import com.example.Order_Service.dto.bill.BillRequestDTO;
import com.example.Order_Service.dto.bill.BillResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Component
public class BillClient {

    private final RestTemplate restTemplate;

    public BillClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BillResponseDTO getBillById(Long billId) {
        String url = "http://localhost:8082/api/bills/" + billId; // 🛠️ adapte le port si besoin
        return restTemplate.getForObject(url, BillResponseDTO.class);
    }

    public Long createBill(BillRequestDTO billRequest) {
        String url = "http://localhost:8082/api/bills";
        return restTemplate.postForObject(url, billRequest, Long.class);
    }
}
