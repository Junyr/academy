package com.prova.academy.controller.DTO;

public record PagamentoResponseDTO(
    Long id,
    Double valor,
    String dataPagamento,
    String status,
    Long alunoId
) {}
