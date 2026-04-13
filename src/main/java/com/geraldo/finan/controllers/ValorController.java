package com.geraldo.finan.controllers;

import com.geraldo.finan.dto.Response;
import com.geraldo.finan.dto.ValorDTO;
import com.geraldo.finan.entities.Valor;
import com.geraldo.finan.repositories.ValorRepository;
import com.geraldo.finan.services.ValorService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/valores")
public class ValorController extends BaseController {

    @Autowired
    private ValorRepository repository;

    @Autowired
    private ValorService valorService;

    @GetMapping
    public Response listar() {
        return success(repository.findAll());
    }

    @GetMapping("/entradas")
    public Response listarEntradas(@RequestParam(required = false) Integer mes,
                                   @RequestParam(required = false) Integer ano) {
        return success(valorService.listarEntradas(mes, ano));
    }

    @GetMapping("/saidas")
    public Response listarSaidas(@RequestParam(required = false) Integer mes,
                                 @RequestParam(required = false) Integer ano) {
        return success(valorService.listarSaidas(mes, ano));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> buscar(@PathVariable Integer id) {
        return repository.findById(id)
                .map(v -> ResponseEntity.ok(success(v)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Response> criar(@RequestBody ValorDTO valorDTO) {
        Valor salvo = valorService.criarValor(valorDTO);
        return created(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> atualizar(@PathVariable Integer id, @RequestBody Valor valor) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        valor.setId(id);
        return ResponseEntity.ok(success(repository.save(valor)));
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
