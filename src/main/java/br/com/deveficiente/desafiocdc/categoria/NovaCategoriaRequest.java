package br.com.deveficiente.desafiocdc.categoria;

import br.com.deveficiente.desafiocdc.compartilhado.CampoUnico;

import javax.validation.constraints.NotBlank;

public record NovaCategoriaRequest(@NotBlank @CampoUnico(campo = "nome", classeDominio = Categoria.class) String nome) {
    public Categoria toModel() {
        return new Categoria(this.nome);
    }
}
