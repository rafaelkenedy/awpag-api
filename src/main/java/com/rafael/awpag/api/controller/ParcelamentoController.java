package com.rafael.awpag.api.controller;


import com.rafael.awpag.api.model.ParcelamentoModel;
import com.rafael.awpag.api.model.input.ParcelamentoInput;
import com.rafael.awpag.assembler.ParcelamentoAssembler;
import com.rafael.awpag.domain.model.Parcelamento;
import com.rafael.awpag.domain.repository.ParcelamentoRepository;
import com.rafael.awpag.domain.service.ParcelamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parcelamentos")
public class ParcelamentoController {

    private final ParcelamentoRepository repository;
    private final ParcelamentoService service;
    private final ParcelamentoAssembler assembler;

    @GetMapping
    public List<ParcelamentoModel> getParcelamentos() {
        return assembler.toCollectionModel(repository.findAll());
    }

    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<ParcelamentoModel> getParcelamentoById(@PathVariable Long parcelamentoId) {
        return repository.findById(parcelamentoId)
                .map(assembler::toModel)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ParcelamentoModel cadastrar(@Valid @RequestBody ParcelamentoInput parcelamentoInput) {
        Parcelamento novoParcelamento = assembler.toEntity(parcelamentoInput);
        Parcelamento parcelamentoCadastrado = service.cadastrar(novoParcelamento);
        return assembler.toModel(parcelamentoCadastrado);
    }
}