package com.prova.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.prova.academy.controller.DTO.PlanoDTO;
import com.prova.academy.controller.DTO.PlanoResponseDTO;
import com.prova.academy.service.PlanoService;

import java.util.List;

@RestController
@RequestMapping("/v1/planos")
public class PlanoController {

    @Autowired
    private PlanoService planoService;

    @GetMapping("/listarTodos")
    public ResponseEntity<List<PlanoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(planoService.listarTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PlanoResponseDTO> buscarPorId(@PathVariable Long id) {
        return planoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<PlanoResponseDTO> salvar(@RequestBody PlanoDTO plano) {
        return ResponseEntity.ok(planoService.salvar(plano));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PlanoResponseDTO> atualizar(@PathVariable Long id, @RequestBody PlanoDTO plano) {
        return ResponseEntity.ok(planoService.atualizar(id, plano));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        planoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
