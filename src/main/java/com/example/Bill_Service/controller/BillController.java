package com.example.Bill_Service.controller;

import com.example.Bill_Service.model.BillModel;
import com.example.Bill_Service.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")

public class BillController {
@Autowired

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }
    @PostMapping("/save")
    public ResponseEntity<BillModel>saveBill(@RequestBody BillModel billModel){
    return ResponseEntity.ok(this.billService.saveBill(billModel));
}
    @GetMapping ("/get")
    public ResponseEntity<List<BillModel>>getBill(){
        return  ResponseEntity.status(HttpStatus.OK).body(billService.getBill());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<BillModel>updateBill(@PathVariable("id") int id,@RequestBody BillModel billModel){
        BillModel foundUpdate=billService.updateBill(id,billModel);
        if (foundUpdate!=null){
            return ResponseEntity.status(HttpStatus.OK).body(billModel);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(billModel);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean>deleteBill(@PathVariable("id") int id ){
        Boolean u=billService.deleteBill(id);
        if (u){
            return ResponseEntity.status(HttpStatus.OK).body(u);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(u);
    }




}


