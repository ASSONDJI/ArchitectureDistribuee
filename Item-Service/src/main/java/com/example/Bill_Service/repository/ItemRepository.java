package com.example.Bill_Service.repository;

import com.example.Bill_Service.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<ItemModel, Integer> {
    @Query("select i from ItemModel i where i.idItem=:id")
    ItemModel findBy(@Param("id") Integer id);

}
