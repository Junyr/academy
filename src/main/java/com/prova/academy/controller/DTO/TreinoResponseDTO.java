package com.prova.academy.controller.DTO;

import java.util.List;

import com.prova.academy.entity.NivelTreino;

public record TreinoResponseDTO(
    Long id,
    String nome,
    List<ExercicioResponseDTO> exercicios,
    NivelTreino nivel
) {}
