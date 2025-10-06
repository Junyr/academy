package com.prova.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.prova.academy.controller.DTO.PagamentoResponseDTO;
import com.prova.academy.service.PagamentoService;

import java.util.List;


@RestController
@RequestMapping("/v1/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping("/listarTodos")
    public ResponseEntity<List<PagamentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pagamentoService.listarTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PagamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return pagamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/pagar/{id}")
    public ResponseEntity<PagamentoResponseDTO> pagar(@PathVariable Long id) {
        return ResponseEntity.ok(pagamentoService.pagar(id));
    }
}

