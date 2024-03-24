package com.rafael.awpag.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Cliente {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
}
