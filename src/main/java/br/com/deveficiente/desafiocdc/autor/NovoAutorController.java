package br.com.deveficiente.desafiocdc.autor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/autor")
public class NovoAutorController {

    private final AutorRepository autorRepository;

    @Autowired
    public NovoAutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Transactional
    @PostMapping
    public void cadastra(@Valid @RequestBody NovoAutorRequest request) {
        var novoAutor = request.toAutor();

        autorRepository.save(novoAutor);
    }
}
