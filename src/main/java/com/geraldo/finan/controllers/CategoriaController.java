package com.geraldo.finan.controllers;

import com.geraldo.finan.dto.Response;
import com.geraldo.finan.entities.Categoria;
import com.geraldo.finan.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController extends BaseController {

    @Autowired
    private CategoriaRepository repository;

    @GetMapping
    public Response listar() {
        return success(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> buscar(@PathVariable Integer id) {
        return repository.findById(id)
                .map(c -> ResponseEntity.ok(success(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Response> criar(@RequestBody Categoria categoria) {
        Categoria salvo = repository.save(categoria);
        return created(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> atualizar(@PathVariable Integer id, @RequestBody Categoria categoria) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoria.setId(id);
        return ResponseEntity.ok(success(repository.save(categoria)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
