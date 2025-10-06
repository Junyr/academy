package com.prova.academy.controller.DTO;

public record PlanoResponseDTO(
    Long id,
    String nome,
    String descricao,
    Double valor,
    Integer duracaoMeses
) {}
