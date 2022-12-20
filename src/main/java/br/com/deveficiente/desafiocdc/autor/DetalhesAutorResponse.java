package br.com.deveficiente.desafiocdc.autor;

public class DetalhesAutorResponse {
    public final String nome;
    public final String descricao;

    public DetalhesAutorResponse(Autor autor) {
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
    }
}
