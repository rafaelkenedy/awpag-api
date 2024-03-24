package com.rafael.awpag.api.controller;

import com.rafael.awpag.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Collections.singletonList;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> getClientes() {
        var cliente = Cliente.builder()
                .id(1L)
                .nome("Rafael")
                .telefone("+55 11 99999-9999")
                .email("rafael@rafael.com")
                .build();

        return singletonList(cliente);
    }

}
