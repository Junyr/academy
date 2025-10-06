package com.prova.academy.controller.DTO;

import com.prova.academy.entity.NivelTreino;

public record TreinoDTO(
    String nome,
    NivelTreino nivel
) {}
