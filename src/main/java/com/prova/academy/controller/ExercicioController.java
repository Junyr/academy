package com.prova.academy.controller;

import com.prova.academy.controller.DTO.ExercicioResponseDTO;
import com.prova.academy.controller.DTO.ExercioDTO;
import com.prova.academy.service.ExercicioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/exercicios")
public class ExercicioController {

    @Autowired
    private ExercicioService exercicioService;

    @GetMapping("/listarTodos")
    public ResponseEntity<List<ExercicioResponseDTO>> listarExercicios() {
        return ResponseEntity.ok(exercicioService.listarTodos());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ExercicioResponseDTO> criarExercicio(
            @RequestBody ExercioDTO exercicio) {
        return ResponseEntity.ok(exercicioService.criarExercicio(exercicio));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ExercicioResponseDTO> atualizarExercicio(
            @PathVariable Long id,
            @RequestBody ExercioDTO exercicio) {
        return ResponseEntity.ok(exercicioService.atualizarExercicio(id, exercicio));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarExercicio(@PathVariable Long id) {
        exercicioService.deletarExercicio(id);
        return ResponseEntity.noContent().build();
    }
}
