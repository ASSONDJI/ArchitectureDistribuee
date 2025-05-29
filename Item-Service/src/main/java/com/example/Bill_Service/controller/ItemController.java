package com.example.Bill_Service.controller;

import com.example.Bill_Service.model.ItemModel;
import com.example.Bill_Service.service.serviceImpl.ItemImplement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "item")
@RequiredArgsConstructor
public class ItemController {
    private  final ItemImplement itemImplement;

    @PostMapping("/add")
    public ItemModel  addItem(@RequestBody ItemModel itemModel){
        try{
            return  itemImplement.add(itemModel);
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }
    @PutMapping("/update/{id}")
    public void updateBill(@PathVariable Integer id, @RequestBody ItemModel itemModel){
       System.out.println(id);
        this.itemImplement.updateBill(id, itemModel);
    }
    @GetMapping("/get-bill")
    public List<ItemModel> getAllBill(){
      return  this.itemImplement.getAll();
    }
    @RequestMapping(path ="/delete-item/{id}")
    public void delete(@PathVariable Integer id){
        this.itemImplement.deleteBill(id);
    }
}
