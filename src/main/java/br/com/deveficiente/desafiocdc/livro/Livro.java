package br.com.deveficiente.desafiocdc.livro;

import br.com.deveficiente.desafiocdc.autor.Autor;
import br.com.deveficiente.desafiocdc.categoria.Categoria;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String titulo;
    @NotBlank
    @Column(nullable = false, length = 500)
    private String resumo;
    @NotBlank
    @Column(nullable = false)
    @Lob
    private String sumario;
    @NotNull
    @Column(nullable = false)
    private BigDecimal preco;
    @NotNull
    @Column(nullable = false)
    private int qtdPaginas;
    @NotBlank
    @Column(nullable = false)
    private String isbn;
    @NotNull
    @Future
    @Column(nullable = false)
    private LocalDate dataEstreia;
    @OneToOne
    @NotNull
    private Categoria categoria;
    @OneToOne
    @NotNull
    private Autor autor;


    public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario,
                 @NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) int qtdPaginas, @NotBlank String isbn,
                 @Future @NotNull @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataEstreia,
                 @NotNull Categoria categoria, @NotNull Autor autor) {
        Assert.state(StringUtils.hasText(titulo), "Título é obrigatório");
        Assert.state(StringUtils.hasText(resumo), "Resumo é obrigatório");
        Assert.state(resumo.length() <= 500, "Resumo é obrigatório");
        Assert.state(StringUtils.hasText(sumario), "Sumário é obrigatório");
        Assert.state(Objects.nonNull(preco), "Preço é obrigatório");
        Assert.state(qtdPaginas >= 100, "Quantidade de páginas deve ser no mínimo 100");
        Assert.state(Objects.nonNull(qtdPaginas), "Quantidade de páginas é obrigatório");
        Assert.state(StringUtils.hasText(isbn), "Isbn é obrigatório");
        Assert.state(Objects.nonNull(dataEstreia), "Data estreia é obrigatório");
        Assert.state(Objects.nonNull(categoria), "Categoria é obrigatório");
        Assert.state(Objects.nonNull(autor), "Autor  é obrigatório");

        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.qtdPaginas = qtdPaginas;
        this.isbn = isbn;
        this.dataEstreia = dataEstreia;
        this.categoria = categoria;
        this.autor = autor;
    }

    @Deprecated
    public Livro() {
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQtdPaginas() {
        return qtdPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataEstreia() {
        return dataEstreia;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Autor getAutor() {
        return autor;
    }
}
