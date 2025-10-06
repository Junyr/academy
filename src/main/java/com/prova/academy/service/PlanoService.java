package com.prova.academy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prova.academy.controller.DTO.PlanoDTO;
import com.prova.academy.controller.DTO.PlanoResponseDTO;
import com.prova.academy.entity.Plano;
import com.prova.academy.repository.PlanoRepository;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;

    public List<PlanoResponseDTO> listarTodos() {
        return planoRepository.findAll().stream()
                .map(plano -> new PlanoResponseDTO(
                        plano.getId(),
                        plano.getNome(),
                        plano.getDescricao(),
                        plano.getValorMensal(),
                        plano.getDuracaoMeses()
                ))
                .toList();
    }

    public Optional<PlanoResponseDTO> buscarPorId(Long id) {
        return planoRepository.findById(id).stream()
            .map(plano -> new PlanoResponseDTO(
                    plano.getId(),
                    plano.getNome(),
                    plano.getDescricao(),
                    plano.getValorMensal(),
                    plano.getDuracaoMeses()
            ))
            .findFirst();
    }

    public PlanoResponseDTO salvar(PlanoDTO planoDTO) {
        if (planoRepository.findByNome(planoDTO.nome()).isPresent()) {
            throw new RuntimeException("Já existe um plano com esse nome.");
        }

        Plano novoPlano = new Plano();
        novoPlano.setNome(planoDTO.nome());
        novoPlano.setDescricao(planoDTO.descricao());
        novoPlano.setValorMensal(planoDTO.valor());
        novoPlano.setDuracaoMeses(planoDTO.duracaoMeses());
        
        planoRepository.save(novoPlano);
        return new PlanoResponseDTO(
                novoPlano.getId(),
                novoPlano.getNome(),
                novoPlano.getDescricao(),
                novoPlano.getValorMensal(),
                novoPlano.getDuracaoMeses()
        );
    }

    public PlanoResponseDTO atualizar(Long id, PlanoDTO planoDTO) {

        Optional<Plano> planoExistente = planoRepository.findById(id);

        if (!planoExistente.isPresent()) {
            throw new RuntimeException("Plano não encontrado.");
        } else{
            Plano atualizarPlano = planoExistente.get();
            atualizarPlano.setNome(planoDTO.nome());
            atualizarPlano.setDescricao(planoDTO.descricao());
            atualizarPlano.setValorMensal(planoDTO.valor());
            atualizarPlano.setDuracaoMeses(planoDTO.duracaoMeses());

            planoRepository.save(atualizarPlano);
            return new PlanoResponseDTO(
                    atualizarPlano.getId(),
                    atualizarPlano.getNome(),
                    atualizarPlano.getDescricao(),
                    atualizarPlano.getValorMensal(),
                    atualizarPlano.getDuracaoMeses()
            );
        }
    }

    public void deletar(Long id) {
        Plano plano = planoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));

        if (!plano.getAlunos().isEmpty()) {
            throw new RuntimeException("Não é possível excluir um plano com alunos vinculados.");
        }

        planoRepository.delete(plano);
    }
}

