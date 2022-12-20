package br.com.deveficiente.desafiocdc.livro;

import br.com.deveficiente.desafiocdc.autor.NovoAutorRequest;
import br.com.deveficiente.desafiocdc.categoria.NovaCategoriaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class NovoLivroControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;


    @Test
    @DisplayName("Deve cadastrar novo livro caso o request esteja válido")
    void teste1() throws Exception {
        var autorRequest = new NovoAutorRequest("autor", "autor@email.com", "novo autor");
        var autorRequestString = mapper.writeValueAsString(autorRequest);
        mockMvc.perform(post("/autor")
                        .contentType(APPLICATION_JSON)
                        .content(autorRequestString))
                .andExpect(status().isOk());

        var categoriaRequest = new NovaCategoriaRequest("categoria");
        var categoriaRequestString = mapper.writeValueAsString(categoriaRequest);

        mockMvc.perform(post("/categoria")
                        .contentType(APPLICATION_JSON)
                        .content(categoriaRequestString))
                .andExpect(status().isOk());

        var livroRequest = new NovoLivroRequest("titulo", "resumo", "sumario",
                BigDecimal.valueOf(30), 100, "ISBN", LocalDate.now().plusDays(1),
                1L, 1L);
        var livroString = mapper.writeValueAsString(livroRequest);

        mockMvc.perform(post("/livros").contentType(APPLICATION_JSON).content(livroString))
                .andExpect(status().isOk());
    }
}