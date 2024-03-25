package com.rafael.awpag.domain.service;

import com.rafael.awpag.domain.exception.EmailEmUsoException;
import com.rafael.awpag.domain.model.Cliente;
import com.rafael.awpag.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CadastroClienteService {

    private final ClienteRepository repository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailEmUso = repository.findByEmail(cliente.getEmail())
                .filter(c -> !c.equals(cliente))
                .isPresent();

        if(emailEmUso){
            throw new EmailEmUsoException(cliente.getEmail());
        }

        return repository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId) {
        repository.deleteById(clienteId);
    }
}
