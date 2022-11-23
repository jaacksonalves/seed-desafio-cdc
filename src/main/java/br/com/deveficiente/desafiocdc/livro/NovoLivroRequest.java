package br.com.deveficiente.desafiocdc.livro;

import br.com.deveficiente.desafiocdc.autor.Autor;
import br.com.deveficiente.desafiocdc.categoria.Categoria;
import br.com.deveficiente.desafiocdc.compartilhado.CampoUnico;
import br.com.deveficiente.desafiocdc.compartilhado.ExistePorCampo;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public record NovoLivroRequest(@NotBlank @CampoUnico(campo = "titulo", classeDominio = Livro.class) String titulo,
                               @NotBlank @Size(max = 500) String resumo,
                               @NotBlank String sumario,
                               @NotNull @Min(20) BigDecimal preco,
                               @NotNull @Min(100) int qtdPaginas,
                               @NotBlank @CampoUnico(campo = "isbn", classeDominio = Livro.class) String isbn,
                               @Future @NotNull LocalDate dataEstreia,
                               @NotNull @ExistePorCampo(campo = "id", classeDominio = Categoria.class) Long idCategoria,
                               @NotNull Long idAutor) {

    public Livro toLivro(EntityManager manager) {
        var categoria = manager.find(Categoria.class, idCategoria);
        Assert.state(Objects.nonNull(categoria), "Categoria é obrigatória, deveria existir nesse ponto por conta da validação feita anteriormente");

        var autor = manager.find(Autor.class, idAutor);
        Assert.state(Objects.nonNull(autor), "Autor é obrigatório, deveria existir nesse ponto por conta da validação feita anteriormente");

        return new Livro(titulo, resumo, sumario, preco, qtdPaginas, isbn, dataEstreia, categoria, autor);
    }
}
