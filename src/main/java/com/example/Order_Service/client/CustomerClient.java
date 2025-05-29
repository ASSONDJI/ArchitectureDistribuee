package com.example.Order_Service.client;

import com.example.Order_Service.dto.customer.CustomerDTO;
import com.example.Order_Service.exceptions.CustomerNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
@Service
public class CustomerClient {
    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 🔎 Récupère les infos d’un client à partir de son ID depuis le Customer-Service.
     * @param id identifiant du client
     * @return CustomerDTO contenant les infos ou null si non trouvé
     */
   public CustomerDTO getCustomerById(Long id) {
        String url = "http://localhost:8081/api/customers/" + id;
        try {
            return restTemplate.getForObject(url, CustomerDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new CustomerNotFoundException(id);
        }
  }
}
