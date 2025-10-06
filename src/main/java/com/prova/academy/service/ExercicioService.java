package com.prova.academy.service;

import com.prova.academy.controller.DTO.ExercicioResponseDTO;
import com.prova.academy.controller.DTO.ExercioDTO;
import com.prova.academy.entity.Exercicio;
import com.prova.academy.entity.Treino;
import com.prova.academy.repository.ExercicioRepository;
import com.prova.academy.repository.TreinoRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private TreinoRepository treinoRepository;

    public List<ExercicioResponseDTO> listarTodos() {
        return exercicioRepository.findAll().stream()
                .map(exercicio -> new ExercicioResponseDTO(
                        exercicio.getId(),
                        exercicio.getDia(),
                        exercicio.getDescricao()
                ))
                .toList();
    }

    public ExercicioResponseDTO criarExercicio(ExercioDTO exercicioDTO) {
        Treino treino = treinoRepository.findById(exercicioDTO.treinoId())
                .orElseThrow(() -> new EntityNotFoundException("Programação de treino não encontrada"));

        Exercicio novoExercicio = new Exercicio();
        novoExercicio.setDia(exercicioDTO.dia());
        novoExercicio.setDescricao(exercicioDTO.descricao());
        novoExercicio.setTreino(treino);
        
        treino.getExercicios().add(novoExercicio);

        treinoRepository.save(treino);

        exercicioRepository.save(novoExercicio);

        return new ExercicioResponseDTO(
                novoExercicio.getId(),
                novoExercicio.getDia(),
                novoExercicio.getDescricao()
        );
    }

    public ExercicioResponseDTO atualizarExercicio(Long id, ExercioDTO exercioDTO) {
        Treino treino = treinoRepository.findById(exercioDTO.treinoId())
                .orElseThrow(() -> new EntityNotFoundException("Programação de treino não encontrada"));

        Exercicio atualizarExercicio = exercicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exercício não encontrado"));

        atualizarExercicio.setDia(exercioDTO.dia());
        atualizarExercicio.setDescricao(exercioDTO.descricao());

        exercicioRepository.save(atualizarExercicio);

        treinoRepository.save(treino);

        return new ExercicioResponseDTO(
            atualizarExercicio.getId(),
            atualizarExercicio.getDia(),
            atualizarExercicio.getDescricao()
    );
    }

    public void deletarExercicio(Long id) {
        if (!exercicioRepository.existsById(id)) {
            throw new EntityNotFoundException("Exercício não encontrado");
        }
        exercicioRepository.deleteById(id);
    }
}
