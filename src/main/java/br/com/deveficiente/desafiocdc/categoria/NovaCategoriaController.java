package br.com.deveficiente.desafiocdc.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class NovaCategoriaController {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public NovaCategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public void cadastra(@Valid @RequestBody NovaCategoriaRequest request) {
        var novaCategoria = request.toModel();

        categoriaRepository.save(novaCategoria);
    }
}
