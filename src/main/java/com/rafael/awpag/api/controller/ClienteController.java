package com.rafael.awpag.api.controller;

import com.rafael.awpag.domain.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClienteController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/clientes")
    public List<Cliente> getClientes() {
        return manager.createQuery("from Cliente", Cliente.class)
                .getResultList();
    }

}
