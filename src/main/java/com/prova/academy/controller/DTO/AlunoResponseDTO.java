package com.prova.academy.controller.DTO;

import java.util.List;

public record AlunoResponseDTO(
    Long id,
    String nome,
    String email,
    String cpf,
    String dataNascimento,
    String dataCadastro,
    String planoNome,
    List<TreinoResponseDTO> treinos,
    List<PagamentoResponseDTO> pagamentos,
    Boolean ativo
) {}
