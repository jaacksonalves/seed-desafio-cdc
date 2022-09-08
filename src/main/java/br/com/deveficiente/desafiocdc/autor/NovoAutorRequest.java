package br.com.deveficiente.desafiocdc.autor;

import br.com.deveficiente.desafiocdc.compartilhado.CampoUnico;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//2.5
public record NovoAutorRequest(//1.5
                               @NotBlank String nome,
                               @NotBlank @Email @CampoUnico(campo = "email", classeDominio = Autor.class) String email,
                               @NotBlank @Size(max = 400) String descricao) {
    //1
    public Autor toModel() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}
