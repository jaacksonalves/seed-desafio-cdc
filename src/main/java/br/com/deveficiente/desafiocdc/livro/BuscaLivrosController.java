package br.com.deveficiente.desafiocdc.livro;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class BuscaLivrosController {

    private final LivroRepository livroRepository;

    @Autowired
    public BuscaLivrosController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @GetMapping
    public List<BuscaLivrosResponse> buscaTodos() {
        var todosOsLivros = livroRepository.findAll();

        return todosOsLivros.stream().map(BuscaLivrosResponse::new).toList();
    }

    @GetMapping("{id}")
    public DetalhesLivroResponse buscaPorId(@PathVariable Long id) {
        var livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));

        return new DetalhesLivroResponse(livro);
    }

}
