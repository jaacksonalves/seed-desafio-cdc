package br.com.desafiocdc.autor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasItem;

@SpringBootTest
@ActiveProfiles("test")
class NovoAutorControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private AutorRepository autorRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    void cleanup() {
        autorRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve criar um autor com sucesso e retornar 200 OK")
    void deveCriarAutorComSucesso() throws Exception {
        var payload = objectMapper.writeValueAsString(
                new NovoAutorRequest(
                        "Fulano de Tal",
                        "fulano@example.com",
                        "Autor de ficção científica com foco em IA."
                )
        );

        mockMvc.perform(post("/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nome").value("Fulano de Tal"))
                .andExpect(jsonPath("$.email").value("fulano@example.com"))
                .andExpect(jsonPath("$.descricao").value("Autor de ficção científica com foco em IA."));

        assertThat(autorRepository.count()).isEqualTo(1);
        var novoAutorSalvo = autorRepository.findAll().getFirst();
        assertThat(novoAutorSalvo.getInstanteRegistro()).isNotNull();
    }

    @Test
    @DisplayName("Deve retornar 400 quando dados obrigatórios são inválidos")
    void deveRetornar400QuandoDadosInvalidos() throws Exception {
        // nome em branco, email inválido e descricao vazia
        var payload = objectMapper.writeValueAsString(
                new NovoAutorRequest(
                        "",
                        "email-invalido",
                        ""
                )
        );

        mockMvc.perform(post("/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest())
                // Deve haver 3 erros de validação: nome em branco, email inválido, descricao vazia
                .andExpect(jsonPath("$.errors.length()").value(3))
                // Verifica mensagens específicas por campo
                .andExpect(jsonPath("$.errors[?(@.field=='nome')].message").value(hasItem("must not be blank")))
                .andExpect(jsonPath("$.errors[?(@.field=='email')].message").value(hasItem("must be a well-formed email address")))
                .andExpect(jsonPath("$.errors[?(@.field=='descricao')].message").value(hasItem("must not be blank")));

        assertThat(autorRepository.count()).isZero();
    }

    @Test
    @DisplayName("Deve retornar 400 quando descricao excede 400 caracteres")
    void deveRetornar400QuandoDescricaoMuitoLonga() throws Exception {
        var longDesc = "a".repeat(401);
        var payload = objectMapper.writeValueAsString(
                new NovoAutorRequest(
                        "Ciclano",
                        "ciclano@example.com",
                        longDesc
                )
        );

        mockMvc.perform(post("/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest())
                // Deve haver 1 erro de validação: descricao com mais de 400 caracteres
                .andExpect(jsonPath("$.errors.length()").value(1))
                // Verifica campo e mensagem do erro de tamanho
                .andExpect(jsonPath("$.errors[0].field").value("descricao"))
                .andExpect(jsonPath("$.errors[0].message").value("size must be between 0 and 400"));

        assertThat(autorRepository.count()).isZero();
    }
}
