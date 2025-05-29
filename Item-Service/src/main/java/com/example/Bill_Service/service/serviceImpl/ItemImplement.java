package com.example.Bill_Service.service.serviceImpl;

import com.example.Bill_Service.model.ItemModel;

import java.util.List;
import java.util.stream.Stream;

public interface ItemImplement {
    ItemModel add(ItemModel itemModel);

    void updateBill(Integer id, ItemModel itemModel);
    

    void deleteBill(Integer id);

    List<ItemModel> getAll();
}
