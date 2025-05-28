package com.example.Custumer_Service.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.example.Custumer_Service.models.CustumerModel;
import com.example.Custumer_Service.repositories.CustumerRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CustumerRepositoryTest {

    @Autowired
    private CustumerRepository custumerRepository;

    @Test
    public void testSaveAndFind() {
        // Given
        CustumerModel custumer = new CustumerModel();
        custumer.setNom_client("John");
        custumer.setPrenom_client("Doe");
        custumer.setAdd_client("123 Main St");

        // When
        custumerRepository.save(custumer);
        List<CustumerModel> found = custumerRepository.findAll();

        // Then
        assertThat(found).hasSize(1);
        CustumerModel savedCustumer = found.get(0);
        assertThat(savedCustumer.getNom_client()).isEqualTo("John");
        assertThat(savedCustumer.getPrenom_client()).isEqualTo("Doe");
        assertThat(savedCustumer.getAdd_client()).isEqualTo("123 Main St");
    }
}