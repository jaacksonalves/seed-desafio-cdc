package br.com.deveficiente.desafiocdc.livro;

public record BuscaLivroResponse(Long id, String titulo) {

    public BuscaLivroResponse(Livro livro) {
        this(livro.getId(), livro.getTitulo());
    }
}
