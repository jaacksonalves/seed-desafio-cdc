package br.com.deveficiente.desafiocdc.livro;

public record BuscaLivrosResponse(Long id, String titulo) {

    public BuscaLivrosResponse(Livro livro) {
        this(livro.getId(), livro.getTitulo());
    }
}
