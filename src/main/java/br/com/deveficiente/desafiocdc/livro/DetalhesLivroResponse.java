package br.com.deveficiente.desafiocdc.livro;

import br.com.deveficiente.desafiocdc.autor.DetalhesAutorResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DetalhesLivroResponse {
    public final String titulo;
    public final String resumo;
    public final String sumario;
    public final BigDecimal preco;
    public final String categoria;
    public final int qtdPaginas;
    public final String isbn;
    public final LocalDate dataEstreia;
    public final DetalhesAutorResponse autor;

    public DetalhesLivroResponse(Livro livro) {
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.categoria = livro.getCategoria().getNome();
        this.qtdPaginas = livro.getQtdPaginas();
        this.isbn = livro.getIsbn();
        this.dataEstreia = livro.getDataEstreia();
        this.autor = new DetalhesAutorResponse(livro.getAutor());
    }


}
