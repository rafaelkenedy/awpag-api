package com.rafael.awpag.api.controller;

import com.rafael.awpag.domain.model.Cliente;
import com.rafael.awpag.domain.repository.ClienteRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    @GetMapping
    public List<Cliente> getClientes() {
        return repository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = repository.findById(clienteId);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cliente addCliente(@Valid @RequestBody Cliente cliente) {
        return repository.save(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente) {
        if (!repository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clienteId);
        cliente = repository.save(cliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> removeCliente(@PathVariable Long clienteId) {
        if (!repository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(clienteId);

        return ResponseEntity.noContent().build();
    }
}