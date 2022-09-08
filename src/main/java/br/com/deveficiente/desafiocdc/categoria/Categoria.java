package br.com.deveficiente.desafiocdc.categoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String nome;

    public Categoria(String nome) {
        this.nome = nome;
    }

    /**
     * @deprecated hibernate apenas, não utilizar!
     */
    @Deprecated(since = "1.0")
    public Categoria() {
    }
}
