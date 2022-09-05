package br.com.deveficiente.desafiocdc.autor;

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
class NovoAutorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("Deve cadastrar novo autor caso o request esteja válido")
    void teste1() throws Exception {
        var request = new NovoAutorRequest("autor", "autor@email.com", "novo autor");
        var requestString = mapper.writeValueAsString(request);
        mockMvc.perform(post("/autor")
                        .contentType(APPLICATION_JSON)
                        .content(requestString))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Não deve cadastrar novo autor caso erros de validação")
    void teste2() throws Exception {
        var request = new NovoAutorRequest("", "", "");
        var requestString = mapper.writeValueAsString(request);
        mockMvc.perform(post("/autor")
                        .contentType(APPLICATION_JSON)
                        .content(requestString))
                .andExpect(status().isBadRequest());
    }
}