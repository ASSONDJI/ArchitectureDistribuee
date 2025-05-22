package com.example.Bill_Service.repository;

import com.example.Bill_Service.model.BillModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest  // Crée un contexte léger avec base en mémoire
public class BillServiceRepositoryTest {

    @Autowired
    private BillRepository billRepository;

    @Test
     void testSaveAndFindAll() {
        BillModel bill = new BillModel();
        bill.setDate_bill(new Date());
        bill.setQuantity_bill(10);
        bill.setLib_bill("Vis");
        bill.setTotalPrice_bill(200.0);

        billRepository.save(bill);

        List<BillModel> bills = billRepository.findAll();

        assertThat(bills).isNotEmpty();
        assertThat(bills.get(0).getLib_bill()).isEqualTo("Vis");
    }

    @Test
     void testFindById() {
        BillModel bill = new BillModel();
        bill.setDate_bill(new Date());
        bill.setQuantity_bill(5);
        bill.setLib_bill("Clou");
        bill.setTotalPrice_bill(100.0);

        BillModel savedBill = billRepository.save(bill);

        var optionalBill = billRepository.findById(savedBill.getId_bill());
        assertThat(optionalBill).isPresent();
        assertThat(optionalBill.get().getLib_bill()).isEqualTo("Clou");
    }


}

