package br.com.desafiocdc.autor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NovoAutorRequest(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank @Size(max = 400) String descricao
) {
    public Autor toModel() {
        return new Autor(nome, email, descricao);
    }
}
