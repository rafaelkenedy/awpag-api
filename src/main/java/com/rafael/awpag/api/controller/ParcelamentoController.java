package com.rafael.awpag.api.controller;


import com.rafael.awpag.domain.exception.EmailEmUsoException;
import com.rafael.awpag.domain.model.Cliente;
import com.rafael.awpag.domain.model.Parcelamento;
import com.rafael.awpag.domain.repository.ParcelamentoRepository;
import com.rafael.awpag.domain.service.ParcelamentoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parcelamentos")
public class ParcelamentoController {

    private final ParcelamentoRepository repository;
    private final ParcelamentoService service;

    @GetMapping
    public List<Parcelamento> getParcelamentos() {
        return repository.findAll();
    }

    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<Parcelamento> getParcelamentoById(@PathVariable Long parcelamentoId) {
        Optional<Parcelamento> parcelamento = repository.findById(parcelamentoId);

        if (parcelamento.isPresent()) {
            return ResponseEntity.ok(parcelamento.get());
        }

        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Parcelamento cadastrar(@Valid @RequestBody Parcelamento parcelamento) {
        return service.cadastrar(parcelamento);
    }

    @ExceptionHandler({EntityNotFoundException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handleBadRequest(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("{\"mensagem\": \"" + e.getMessage() + "\"}");
    }
}
