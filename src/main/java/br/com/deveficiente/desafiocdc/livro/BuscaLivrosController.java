package br.com.deveficiente.desafiocdc.livro;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<BuscaLivroResponse> buscaTodos() {
        var todosOsLivros = livroRepository.findAll();

        return todosOsLivros.stream().map(BuscaLivroResponse::new).toList();
    }

}
