package com.example.Bill_Service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bill_Service.model.BillModel;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<BillModel, Integer> {

}
