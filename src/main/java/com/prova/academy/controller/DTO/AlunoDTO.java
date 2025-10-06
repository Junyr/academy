package com.prova.academy.controller.DTO;

import java.time.LocalDate;
import java.util.List;

public record AlunoDTO(
    String nome,
    String email,
    String cpf,
    LocalDate dataNascimento,
    Long planoId,
    List<Long> treinoId
) {}
