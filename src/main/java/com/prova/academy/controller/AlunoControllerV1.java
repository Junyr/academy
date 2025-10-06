package com.prova.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prova.academy.controller.DTO.AlunoResponseDTO;
import com.prova.academy.service.AlunoService;

@RestController
@RequestMapping("/v1.1/alunos")
public class AlunoControllerV1 {

    @Autowired
    private AlunoService alunoService;

    @PutMapping("/reativar/{id}")
    public ResponseEntity<AlunoResponseDTO> reativar(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.reativar(id));
    }

    @PutMapping("/inativar/{id}")
    public ResponseEntity<AlunoResponseDTO> inativar(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.inativar(id));
    }

}
