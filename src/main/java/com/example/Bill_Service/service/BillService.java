package com.example.Bill_Service.service;

import com.example.Bill_Service.model.BillModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BillService {



     public BillModel saveBill( BillModel billModel);

     public List<BillModel> getBill();

     public BillModel updateBill(int id, BillModel billModel);

     public boolean deleteBill(int id);

}
