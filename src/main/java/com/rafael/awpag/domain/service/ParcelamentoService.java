package com.rafael.awpag.domain.service;

import com.rafael.awpag.domain.model.Cliente;
import com.rafael.awpag.domain.model.Parcelamento;
import com.rafael.awpag.domain.repository.ParcelamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class ParcelamentoService {

    private final ParcelamentoRepository repository;
    private final CadastroClienteService cadastroClienteService;

    @Transactional
    public Parcelamento cadastrar(Parcelamento parcelamento) {

        if (parcelamento.getId() != null) {
            throw new IllegalArgumentException("Código do parcelamento não deve ser null.");
        }

        Cliente cliente = cadastroClienteService.buscar(parcelamento.getCliente().getId());

        parcelamento.setCliente(cliente);
        parcelamento.setDataCriacao(OffsetDateTime.now());

        return repository.save(parcelamento);
    }
}
