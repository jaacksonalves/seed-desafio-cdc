package br.com.deveficiente.desafiocdc.autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record NovoAutorRequest(@NotBlank String nome,
                               @NotBlank @Email String email,
                               @NotBlank @Size(max = 400) String descricao) {
    public Autor toAutor() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}
