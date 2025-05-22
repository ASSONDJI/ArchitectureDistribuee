package com.example.Bill_Service.service.serviceImpl;

import com.example.Bill_Service.exception.BillNotFoundException;
import com.example.Bill_Service.model.BillModel;
import com.example.Bill_Service.repository.BillRepository;
import com.example.Bill_Service.service.BillService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;

    public  BillServiceImpl(BillRepository billRepository){
        this.billRepository= billRepository;
    }



    @Override
    public BillModel saveBill(BillModel billModel) {
            return   this.billRepository.save(billModel);
    }

    @Override
    public List<BillModel> getBill(){
        return this.billRepository.findAll();
    }

    @Override
    public BillModel updateBill(int id, BillModel billModel){
        Optional<BillModel> foundBill= this.billRepository.findById(id);
        if (id== billModel.getId_bill() && foundBill.isPresent())
        {
            return this.billRepository.save(billModel);
        }
        throw new BillNotFoundException(id);
    }
    @Override
    public boolean deleteBill(int id){
        Optional<BillModel> foundBill= this.billRepository.findById(id);
        if (foundBill.isPresent()){
            BillModel u= foundBill.get();
            this.billRepository.delete(u);
            return true;
        }
        throw new BillNotFoundException((id));
    }

}
