package com.prova.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.prova.academy.controller.DTO.TreinoDTO;
import com.prova.academy.controller.DTO.TreinoResponseDTO;
import com.prova.academy.service.TreinoService;

import java.util.List;

@RestController
@RequestMapping("/v1/treinos")
public class TreinoController {

    @Autowired
    private TreinoService treinoService;

    @GetMapping("/listarTodos")
    public ResponseEntity<List<TreinoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(treinoService.listarTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<TreinoResponseDTO> buscarPorId(@PathVariable Long id) {
        return treinoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<TreinoResponseDTO> salvar(@RequestBody TreinoDTO treino) {
        return ResponseEntity.ok(treinoService.salvar(treino));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<TreinoResponseDTO> atualizar(@PathVariable Long id, @RequestBody TreinoDTO treino) {
        return ResponseEntity.ok(treinoService.atualizar(id, treino));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        treinoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

