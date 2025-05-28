package com.example.Custumer_Service.Services;

import com.example.Custumer_Service.Services.ImplService.CustumerServiceImpl;
import com.example.Custumer_Service.dto.CustumerRequestDto;
import com.example.Custumer_Service.dto.CustumerResponseDto;
import com.example.Custumer_Service.exceptions.CustumerNotFoundException;
import com.example.Custumer_Service.mapper.CustumerMapper;
import com.example.Custumer_Service.models.CustumerModel;
import com.example.Custumer_Service.repositories.CustumerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustumerServiceTest {

    private CustumerRepository custumerRepository;
    private CustumerMapper custumerMapper;
    private CustumerService custumerService;

    @BeforeEach
    void setUp() {
        custumerRepository = mock(CustumerRepository.class);
        custumerMapper = new CustumerMapper();
        custumerService = new CustumerServiceImpl(custumerRepository, custumerMapper);
    }

    @Test
    void testCreateCustumer() {
        CustumerRequestDto dto = new CustumerRequestDto("Doe", "John", "Paris");
        CustumerModel entity = custumerMapper.toEntity(dto);
        entity.setId_client(1L);

        when(custumerRepository.save(any())).thenReturn(entity);

        CustumerResponseDto result = custumerService.createCustumer(dto);

        assertEquals("Doe", result.nom_client());
        assertEquals("John", result.prenom_client());
        assertEquals("Paris", result.add_client());
        verify(custumerRepository).save(any());
    }

    @Test
    void testGetAllCustumers() {
        CustumerModel c1 = new CustumerModel(1L, "Doe", "John", "Paris");
        CustumerModel c2 = new CustumerModel(2L, "Smith", "Alice", "Lyon");

        when(custumerRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<CustumerResponseDto> result = custumerService.getAllCustumers();

        assertEquals(2, result.size());
        assertEquals("Doe", result.get(0).nom_client());
    }

    @Test
    void testGetCustumerById() {
        CustumerModel c = new CustumerModel(1L, "Doe", "John", "Paris");

        when(custumerRepository.findById(1L)).thenReturn(Optional.of(c));

        CustumerResponseDto result = custumerService.getCustumerById(1L);

        assertEquals("Doe", result.nom_client());
        assertEquals("John", result.prenom_client());
    }

    @Test
    void testGetCustumerByIdNotFound() {
        when(custumerRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(CustumerNotFoundException.class, () -> custumerService.getCustumerById(99L));
    }

    @Test
    void testUpdateCustumer() {
        CustumerModel existing = new CustumerModel(1L, "Old", "Name", "OldAddress");
        CustumerRequestDto dto = new CustumerRequestDto("New", "Name", "NewAddress");

        when(custumerRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(custumerRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        CustumerResponseDto updated = custumerService.updateCustumer(1L, dto);

        assertEquals("New", updated.nom_client());
        assertEquals("Name", updated.prenom_client());
        assertEquals("NewAddress", updated.add_client());
    }

    @Test
    void testDeleteCustumer() {
        CustumerModel existing = new CustumerModel(1L, "Doe", "John", "Paris");

        when(custumerRepository.findById(1L)).thenReturn(Optional.of(existing));
        doNothing().when(custumerRepository).delete(existing);

        custumerService.deleteCustumer(1L);

        verify(custumerRepository).delete(existing);
    }

    @Test
    void testDeleteCustumerNotFound() {
        when(custumerRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(CustumerNotFoundException.class, () -> custumerService.deleteCustumer(2L));
    }
}
