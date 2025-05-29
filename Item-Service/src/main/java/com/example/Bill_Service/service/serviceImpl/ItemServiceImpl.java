package com.example.Bill_Service.service.serviceImpl;

import com.example.Bill_Service.model.ItemModel;
import com.example.Bill_Service.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements  ItemImplement {
   private  final ItemRepository itemRepository;

   @Override
   public ItemModel add(ItemModel itemModel) {
      return this.itemRepository.save(itemModel);
   }
   @Override
   public void updateBill(Integer id, ItemModel itemModel) {
      ItemModel itemModelbd = this.itemRepository.findById(id).orElseThrow();
      itemModelbd.setName(itemModel.getName());
      itemModelbd.setQuantity(itemModel.getQuantity());
      itemModelbd.setUnitPrice(itemModel.getUnitPrice());
      itemModelbd.setAmount(itemModel.getAmount());
      this.itemRepository.save(itemModelbd);
   }
   @Override
   public void deleteBill(Integer id) {
      this.itemRepository.deleteById(id);
   }

   @Override
   public List<ItemModel> getAll() {
      return this.itemRepository.findAll();
   }
}
