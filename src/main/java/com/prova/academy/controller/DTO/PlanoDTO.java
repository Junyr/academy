package com.prova.academy.controller.DTO;

public record PlanoDTO(
    String nome,
    String descricao,
    Double valor,
    Integer duracaoMeses
) {}
