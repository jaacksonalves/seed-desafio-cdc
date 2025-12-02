package br.com.desafiocdc.autor;

import java.time.Instant;

public record NovoAutorResponse(
        Long id,
        String nome,
        String email,
        String descricao,
        Instant instanteRegistro
) {
    public NovoAutorResponse(Autor autor) {
        this(autor.getId(), autor.getNome(), autor.getEmail(), autor.getDescricao(), autor.getInstanteRegistro());
    }
}
