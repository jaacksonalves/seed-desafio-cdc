package br.com.deveficiente.desafiocdc.autor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

//3
@RestController
@RequestMapping("/autor")
public class NovoAutorController {

    //1
    private final AutorRepository autorRepository;

    @Autowired
    public NovoAutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    //1
    @Transactional
    @PostMapping
    public void cadastra(@Valid @RequestBody NovoAutorRequest request) {
        //1
        var novoAutor = request.toModel();

        autorRepository.save(novoAutor);
    }
}
