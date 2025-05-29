package com.example.Order_Service.client;

import com.example.Order_Service.dto.product.ProductDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class ProductClient {

    private final RestTemplate restTemplate;

    public ProductClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 🔍 Récupère les informations d’un produit par son ID depuis Product-Service
     * @param productId l'identifiant du produit
     * @return ProductDTO ou null si non trouvé
     */
    public ProductDTO getProductById(Long productId) {
        String url = "http://localhost:8082/api/products/" + productId;
        try {
            return restTemplate.getForObject(url, ProductDTO.class);
        } catch (Exception e) {
            return null; // ou logger l’erreur si besoin
        }
    }
}
