package com.prova.academy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prova.academy.controller.DTO.PagamentoResponseDTO;
import com.prova.academy.service.PagamentoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/v1.1/pagamentos")
public class PagamentoControllerV1 {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping("/listarPedentes")
    public ResponseEntity<List<PagamentoResponseDTO>> listarPedentes() {
        return ResponseEntity.ok(pagamentoService.listarPendentes());
    }

    @GetMapping("/listarAluno/{id}")
    public ResponseEntity<List<PagamentoResponseDTO>> listarAlunos(@PathVariable Long id) {
        return ResponseEntity.ok(pagamentoService.listarPorAluno(id));
    }
    
}
