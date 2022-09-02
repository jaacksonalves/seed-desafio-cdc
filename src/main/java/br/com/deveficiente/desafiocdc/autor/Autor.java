package br.com.deveficiente.desafiocdc.autor;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String nome;
    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;
    @NotBlank
    @Size(max = 400)
    @Column(nullable = false, length = 400)
    private String descricao;
    @NotNull
    @Column(nullable = false, updatable = false)
    private final Instant instanteCriacao = Instant.now();

    public Autor(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size(max = 400) String descricao) {
        Assert.state(StringUtils.hasText(nome), "nome é obrigatório");
        Assert.state(StringUtils.hasText(email), "email é obrigatório");
        Assert.state(StringUtils.hasText(descricao), "descricao é obrigatório");

        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }


    /**
     * @deprecated hibernate apenas, não utilizar!
     */
    @Deprecated(since = "1.0")
    public Autor() {
    }
}
