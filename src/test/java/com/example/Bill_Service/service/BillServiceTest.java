package com.example.Bill_Service.service;

import com.example.Bill_Service.model.BillModel;
import com.example.Bill_Service.repository.BillRepository;
import com.example.Bill_Service.service.serviceImpl.BillServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BillServiceTest {

    @Mock
    private BillRepository billRepository;

    @InjectMocks
    private BillServiceImpl billService;

    private BillModel mockBill;

    @BeforeEach
    void setUp() {
        mockBill = new BillModel();
        mockBill.setId_bill(1);
        mockBill.setDate_bill(new Date());
        mockBill.setQuantity_bill(10);
        mockBill.setLib_bill("Clous");
        mockBill.setTotalPrice_bill(500.0);
    }

    @Test
    void testSaveBill() {
        when(billRepository.save(any(BillModel.class))).thenReturn(mockBill);

        BillModel savedBill = billService.saveBill(mockBill);

        assertNotNull(savedBill);
        assertEquals("Clous", savedBill.getLib_bill());

        verify(billRepository, times(1)).save(any(BillModel.class));
    }

    @Test
    void testGetBill() {
        when(billRepository.findAll()).thenReturn(List.of(mockBill));

        List<BillModel> bills = billService.getBill();

        assertEquals(1, bills.size());
        assertEquals("Clous", bills.get(0).getLib_bill());

        verify(billRepository, times(1)).findAll();
    }

    @Test
    void testDeleteBill_Success() {
        when(billRepository.findById(1)).thenReturn(Optional.of(mockBill));
        doNothing().when(billRepository).delete(mockBill);

        boolean result = billService.deleteBill(1);

        assertTrue(result);
        verify(billRepository, times(1)).delete(mockBill);
    }

    @Test
    void testUpdateBill_Success() {
        when(billRepository.findById(1)).thenReturn(Optional.of(mockBill));
        // Retourne l'objet passé dans save()
        when(billRepository.save(any(BillModel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BillModel updatedBill = new BillModel(1, new Date(), 15, "Clous galvanisé", 750.0);

        BillModel result = billService.updateBill(1, updatedBill);

        assertNotNull(result);
        assertEquals("Clous galvanisé", result.getLib_bill());
        assertEquals(15, result.getQuantity_bill());
        assertEquals(750.0, result.getTotalPrice_bill());

        verify(billRepository, times(1)).findById(1);
        verify(billRepository, times(1)).save(updatedBill);
    }


}
