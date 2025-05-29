package com.example.Order_Service.controllers;

import com.example.Order_Service.dto.item.OrderResponseDTO;
import com.example.Order_Service.dto.order.OrderCreateDTO;
import com.example.Order_Service.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 🎯 Contrôleur principal pour gérer les opérations sur les commandes.
 * Les données enrichies proviennent de Customer-Service, Product-Service et Bill-Service.
 */

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*") // Pour permettre les appels depuis Angular ou autres clients
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * ✅ Créer une commande.
     * Appelle en cascade les services externes :
     * - CustomerService pour vérifier le client
     * - ProductService pour valider les produits
     * - BillService pour générer la facture
     */
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderCreateDTO dto) {
        return ResponseEntity.ok(orderService.createOrder(dto));
    }

    /**
     * ✅ Obtenir les détails complets d’une commande par son ID :
     * - Informations du client
     * - Produits commandés
     * - Montant total
     * - Identifiant de la facture
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    /**
     * ✅ Obtenir la liste de toutes les commandes existantes,
     * enrichies avec les informations liées.
     */
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    /**
     * ✅ Supprimer une commande de la base.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * ✅ Modifier une commande existante :
     * - Valide les données d’entrée
     * - Met à jour les produits, le statut, etc.
     * - Met à jour la facture si nécessaire
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrder(
            @PathVariable Long id,
            @Valid @RequestBody OrderCreateDTO dto) {
        OrderResponseDTO updatedOrder = orderService.updateOrder(id, dto);
        return ResponseEntity.ok(updatedOrder);
    }
}
