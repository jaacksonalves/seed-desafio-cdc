package br.com.desafiocdc.autor;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autores")
public class NovoAutorController {

    private final AutorRepository autorRepository;

    public NovoAutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @PostMapping
    public ResponseEntity<NovoAutorResponse> criar(@RequestBody @Valid NovoAutorRequest request) {
        var autor = request.toModel();
        autor = autorRepository.save(autor);
        return ResponseEntity.ok(new NovoAutorResponse(autor));
    }
}
