package com.example.Custumer_Service.Controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;

import com.example.Custumer_Service.Services.CustumerService;
import com.example.Custumer_Service.controllers.CustumerController;
import com.example.Custumer_Service.dto.CustumerResponseDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.*;

@WebMvcTest(CustumerController.class)
public class CustumerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustumerService custumerService;

    @Test
    public void testGetAllCustumers() throws Exception {
        CustumerResponseDto custumer1 = new CustumerResponseDto(1L, "Dupont", "Jean", "Paris");
        CustumerResponseDto custumer2 = new CustumerResponseDto(2L, "Martin", "Claire", "Lyon");

        given(custumerService.getAllCustumers()).willReturn(Arrays.asList(custumer1, custumer2));

        mockMvc.perform(get("/custumers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id_client", is(1)))
                .andExpect(jsonPath("$[0].nom_client", is("Dupont")))
                .andExpect(jsonPath("$[0].prenom_client", is("Jean")))
                .andExpect(jsonPath("$[0].add_client", is("Paris")))
                .andExpect(jsonPath("$[1].id_client", is(2)))
                .andExpect(jsonPath("$[1].nom_client", is("Martin")))
                .andExpect(jsonPath("$[1].prenom_client", is("Claire")))
                .andExpect(jsonPath("$[1].add_client", is("Lyon")));
    }
}
