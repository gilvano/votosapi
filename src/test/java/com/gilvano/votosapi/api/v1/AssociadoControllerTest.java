package com.gilvano.votosapi.api.v1;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gilvano.votosapi.api.v1.request.AssociadoRequest;
import com.gilvano.votosapi.model.Associado;
import com.gilvano.votosapi.service.AssociadoService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = {AssociadoController.class}) 
@AutoConfigureMockMvc
public class AssociadoControllerTest {

    static String ASSOCIADO_API = "/api/v1/associado";

    @Autowired
    MockMvc mvc; 

    @MockBean
    AssociadoService service;

    @Test
    @DisplayName("Deve cadastrar um novo associado")
    public void cadastrarAssociadoTest() throws Exception {
        AssociadoRequest associadoRequest = new AssociadoRequest("Associado 1", "34111528007");

        Associado associado = Associado
                                    .builder()
                                    .id(1L)
                                    .nome("Associado 1")
                                    .cpf("34111528007")
                                    .build();

        BDDMockito
                .given(service.salvar(Mockito.any(Associado.class)))
                .willReturn(associado);

        String json = new ObjectMapper().writeValueAsString(associadoRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(ASSOCIADO_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("nome").value(associadoRequest.getNome()))
                .andExpect(jsonPath("cpf").value(associadoRequest.getCpf()));
    }

    @Test
    @DisplayName("Deve retornar um BadRequest ao cadastrar um associado invalido")
    public void cadastrarAssociadoInvalidoTest() throws Exception {
        AssociadoRequest associadoRequest = new AssociadoRequest();

        String json = new ObjectMapper().writeValueAsString(associadoRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(ASSOCIADO_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
                
         mvc.perform(request)
                 .andExpect(status().isBadRequest());
    }    
}
