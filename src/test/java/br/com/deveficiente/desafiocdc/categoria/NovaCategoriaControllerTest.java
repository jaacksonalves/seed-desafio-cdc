package br.com.deveficiente.desafiocdc.categoria;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class NovaCategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("Deve cadastrar nova categoria caso o request esteja válido")
    void teste1() throws Exception {
        var categoria = new NovaCategoriaRequest("categoria");
        var categoriaString = mapper.writeValueAsString(categoria);

        mockMvc.perform(post("/categoria")
                        .contentType(APPLICATION_JSON)
                        .content(categoriaString))
                .andExpect(status().isOk());
    }
}