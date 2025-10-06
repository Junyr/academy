package com.prova.academy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prova.academy.controller.DTO.TreinoDTO;
import com.prova.academy.controller.DTO.TreinoResponseDTO;
import com.prova.academy.controller.DTO.ExercicioResponseDTO;
import com.prova.academy.entity.Treino;
import com.prova.academy.repository.TreinoRepository;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    public List<TreinoResponseDTO> listarTodos() {
        return treinoRepository.findAll().stream()
            .map(treino -> new TreinoResponseDTO(
                    treino.getId(),
                    treino.getNome(),
                    treino.getExercicios().stream()
                        .map(exercicio -> new ExercicioResponseDTO(
                            exercicio.getId(),
                            exercicio.getDia(),
                            exercicio.getDescricao()
                        ))
                        .toList(),
                    treino.getNivel()
            ))
            .toList();
    }

    public Optional<TreinoResponseDTO> buscarPorId(Long id) {
        return treinoRepository.findById(id).stream()
            .map(treino -> new TreinoResponseDTO(
                    treino.getId(),
                    treino.getNome(),
                    treino.getExercicios().stream()
                        .map(exercicio -> new ExercicioResponseDTO(
                            exercicio.getId(),
                            exercicio.getDia(),
                            exercicio.getDescricao()
                        ))
                        .toList(),
                    treino.getNivel()
            ))
            .findFirst();
    }

    public TreinoResponseDTO salvar(TreinoDTO treinoDTO) {
        Treino novoTreino = new Treino();
        novoTreino.setNome(treinoDTO.nome());
        novoTreino.setNivel(treinoDTO.nivel());
        
        treinoRepository.save(novoTreino);
        return new TreinoResponseDTO(
            novoTreino.getId(),
            novoTreino.getNome(),
            novoTreino.getExercicios().stream()
                .map(exercicio -> new ExercicioResponseDTO(
                    exercicio.getId(),
                    exercicio.getDia(),
                    exercicio.getDescricao()
                ))
                .toList(),
            novoTreino.getNivel()
        );
    }

    public TreinoResponseDTO atualizar(Long id, TreinoDTO treinoDTO) {
        Optional<Treino> treinoExistente = treinoRepository.findById(id);

        if (!treinoExistente.isPresent()) {
            throw new RuntimeException("Treino não encontrado");
        } else {
            Treino atualizarTreino = treinoExistente.get();
            atualizarTreino.setNome(treinoDTO.nome());
            atualizarTreino.setNivel(treinoDTO.nivel());

            treinoRepository.save(atualizarTreino);
            
            return new TreinoResponseDTO(
                    atualizarTreino.getId(),
                    atualizarTreino.getNome(),
                    atualizarTreino.getExercicios().stream()
                        .map(exercicio -> new ExercicioResponseDTO(
                            exercicio.getId(),
                            exercicio.getDia(),
                            exercicio.getDescricao()
                        ))
                        .toList(),
                    atualizarTreino.getNivel()
            );
        }
    }

    // Deletar treino (só se não tiver alunos vinculados)
    public void deletar(Long id) {
        Treino treino = treinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado"));

        if (!treino.getAlunos().isEmpty()) {
            throw new RuntimeException("Não é possível excluir treino com alunos vinculados.");
        }

        treinoRepository.delete(treino);
    }
}

