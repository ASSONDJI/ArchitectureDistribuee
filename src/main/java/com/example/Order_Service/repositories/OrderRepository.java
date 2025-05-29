package com.example.Order_Service.repositories;

import com.example.Order_Service.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrderRepository extends JpaRepository<Order, Long> {


}
