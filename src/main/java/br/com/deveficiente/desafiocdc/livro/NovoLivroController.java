package br.com.deveficiente.desafiocdc.livro;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/livros")
public class NovoLivroController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public void cadastra(@RequestBody @Valid NovoLivroRequest request) {
        var novoLivro = request.toLivro(manager);

        manager.persist(novoLivro);
    }
}
