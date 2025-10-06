package com.prova.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.prova.academy.controller.DTO.AlunoDTO;
import com.prova.academy.controller.DTO.AlunoResponseDTO;
import com.prova.academy.service.AlunoService;

import java.util.List;

@RestController
@RequestMapping("/v1/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/listarTodos")
    public ResponseEntity<List<AlunoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<AlunoResponseDTO> buscarPorId(@PathVariable Long id) {
        return alunoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<AlunoResponseDTO> salvar(@RequestBody AlunoDTO aluno) {
        return ResponseEntity.ok(alunoService.salvar(aluno));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizar(@PathVariable Long id, @RequestBody AlunoDTO aluno) {
        return ResponseEntity.ok(alunoService.atualizarDados(id, aluno));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

