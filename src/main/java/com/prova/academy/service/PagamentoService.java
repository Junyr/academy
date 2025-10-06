package com.prova.academy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prova.academy.controller.DTO.PagamentoDTO;
import com.prova.academy.controller.DTO.PagamentoResponseDTO;
import com.prova.academy.entity.Pagamento;
import com.prova.academy.entity.StatusPagamento;
import com.prova.academy.repository.AlunoRepository;
import com.prova.academy.repository.PagamentoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public List<PagamentoResponseDTO> listarTodos() {
        return pagamentoRepository.findAll().stream()
            .map(p -> new PagamentoResponseDTO(
                p.getId(),
                p.getValor(),
                p.getDataPagamento().toString(),
                p.getStatus().name(),
                p.getAluno().getId()
            )).toList();
    }

    public Optional<PagamentoResponseDTO> buscarPorId(Long id) {
        return pagamentoRepository.findById(id).stream()
            .map(p -> new PagamentoResponseDTO(
                p.getId(),
                p.getValor(),
                p.getDataPagamento().toString(),
                p.getStatus().name(),
                p.getAluno().getId()
            )).findFirst();
    }

    public PagamentoResponseDTO salvar(PagamentoDTO pagamentoDTO) {
        Pagamento novoPagamento = new Pagamento();
        novoPagamento.setValor(pagamentoDTO.valor());
        novoPagamento.setAluno(alunoRepository.findById(pagamentoDTO.alunoId())
            .orElseThrow(() -> new RuntimeException("Aluno não encontrado")));
        
        pagamentoRepository.save(novoPagamento);

        return new PagamentoResponseDTO(
            novoPagamento.getId(),
            novoPagamento.getValor(),
            novoPagamento.getDataPagamento().toString(),
            novoPagamento.getStatus().name(),
            novoPagamento.getAluno().getId()
        );
    }

    public PagamentoResponseDTO atualizar(Long id, PagamentoDTO pagamentoDTO) {
        Pagamento atualizarPagamento = pagamentoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        atualizarPagamento.setValor(pagamentoDTO.valor());
        atualizarPagamento.setAluno(alunoRepository.findById(pagamentoDTO.alunoId())
            .orElseThrow(() -> new RuntimeException("Aluno não encontrado")));
        
        pagamentoRepository.save(atualizarPagamento);

        return new PagamentoResponseDTO(
            atualizarPagamento.getId(),
            atualizarPagamento.getValor(),
            atualizarPagamento.getDataPagamento().toString(),
            atualizarPagamento.getStatus().name(),
            atualizarPagamento.getAluno().getId()
        );
    }

    public void deletar(Long id) {
        if(!pagamentoRepository.existsById(id)){
            throw new EntityNotFoundException("Pagamento não encontrado");
        }
        pagamentoRepository.deleteById(id);
    }

    public PagamentoResponseDTO pagar(Long id) {
        Pagamento atualizarPagamento = pagamentoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        atualizarPagamento.setStatus(StatusPagamento.PAGO);
        pagamentoRepository.save(atualizarPagamento);

        return new PagamentoResponseDTO(
            atualizarPagamento.getId(),
            atualizarPagamento.getValor(),
            atualizarPagamento.getDataPagamento().toString(),
            atualizarPagamento.getStatus().name(),
            atualizarPagamento.getAluno().getId()
        );
    }

    public List<PagamentoResponseDTO> listarPorAluno(Long alunoId) {
        return pagamentoRepository.findByAlunoId(alunoId).stream()
            .map(p -> new PagamentoResponseDTO(
                p.getId(),
                p.getValor(),
                p.getDataPagamento().toString(),
                p.getStatus().name(),
                p.getAluno().getId()
            )).toList();
    }

    public List<PagamentoResponseDTO> listarPendentes() {
        return pagamentoRepository.findByStatus(StatusPagamento.PENDENTE).stream()
            .map(p -> new PagamentoResponseDTO(
                p.getId(),
                p.getValor(),
                p.getDataPagamento().toString(),
                p.getStatus().name(),
                p.getAluno().getId()
            )).toList();
    }
}

