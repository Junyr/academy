package com.prova.academy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prova.academy.controller.DTO.AlunoDTO;
import com.prova.academy.controller.DTO.AlunoResponseDTO;
import com.prova.academy.controller.DTO.ExercicioResponseDTO;
import com.prova.academy.controller.DTO.PagamentoDTO;
import com.prova.academy.controller.DTO.PagamentoResponseDTO;
import com.prova.academy.controller.DTO.TreinoResponseDTO;
import com.prova.academy.entity.Aluno;
import com.prova.academy.repository.AlunoRepository;
import com.prova.academy.repository.PlanoRepository;
import com.prova.academy.repository.TreinoRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private PlanoRepository planoRepository;

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private PagamentoService pagamentoService;

    public List<AlunoResponseDTO> listarTodos() {
        return alunoRepository.findAll().stream()
            .map(aluno -> new AlunoResponseDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getCpf(),
                aluno.getDataNascimento() != null ? aluno.getDataNascimento().toString() : null,
                aluno.getDataCadastro() != null ? aluno.getDataCadastro().toString() : null,
                aluno.getPlano() != null ? aluno.getPlano().getNome() : null,
                aluno.getTreino().stream()
                    .map(treino -> new TreinoResponseDTO(
                        treino.getId(),
                        treino.getNome(),
                        treino.getExercicios().stream()
                            .map(exercicio -> new com.prova.academy.controller.DTO.ExercicioResponseDTO(
                                exercicio.getId(),
                                exercicio.getDia(),
                                exercicio.getDescricao()
                            ))
                            .toList(),
                        treino.getNivel()
                    )).toList(),
                aluno.getPagamento().stream()
                    .map(p -> new PagamentoResponseDTO(
                        p.getId(),
                        p.getValor(),
                        p.getDataPagamento().toString(),
                        p.getStatus().name(),
                        p.getAluno().getId()
                    )).toList(),
                aluno.getAtivo()
            )).toList();
    }

    public Optional<AlunoResponseDTO> buscarPorId(Long id) {
        return alunoRepository.findById(id).stream()
            .map(aluno -> new AlunoResponseDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getCpf(),
                aluno.getDataNascimento() != null ? aluno.getDataNascimento().toString() : null,
                aluno.getDataCadastro() != null ? aluno.getDataCadastro().toString() : null,
                aluno.getPlano() != null ? aluno.getPlano().getNome() : null,
                aluno.getTreino().stream()
                    .map(treino -> new TreinoResponseDTO(
                        treino.getId(),
                        treino.getNome(),
                        treino.getExercicios().stream()
                            .map(exercicio -> new com.prova.academy.controller.DTO.ExercicioResponseDTO(
                                exercicio.getId(),
                                exercicio.getDia(),
                                exercicio.getDescricao()
                            ))
                            .toList(),
                        treino.getNivel()
                    )).toList(),
                aluno.getPagamento().stream()
                    .map(p -> new com.prova.academy.controller.DTO.PagamentoResponseDTO(
                        p.getId(),
                        p.getValor(),
                        p.getDataPagamento().toString(),
                        p.getStatus().name(),
                        p.getAluno().getId()
                    )).toList(),
                aluno.getAtivo()
            )).findFirst();
    }

    public AlunoResponseDTO salvar(AlunoDTO alunoDTO) {
        Aluno novoAluno = new Aluno();
        novoAluno.setNome(alunoDTO.nome());
        novoAluno.setEmail(alunoDTO.email());
        novoAluno.setCpf(alunoDTO.cpf());
        novoAluno.setDataNascimento(alunoDTO.dataNascimento());
        novoAluno.setPlano(planoRepository.findById(alunoDTO.planoId()).get());
        novoAluno.setTreino(treinoRepository.findAllById(alunoDTO.treinoId()));
        
        alunoRepository.save(novoAluno);

        pagamentoService.salvar(new PagamentoDTO(
            novoAluno.getPlano().getValorMensal(),
            novoAluno.getId()
        ));

        return new AlunoResponseDTO(
            novoAluno.getId(),
            novoAluno.getNome(),
            novoAluno.getEmail(),
            novoAluno.getCpf(),
            novoAluno.getDataNascimento() != null ? novoAluno.getDataNascimento().toString() : null,
            novoAluno.getDataCadastro() != null ? novoAluno.getDataCadastro().toString() : null,
            novoAluno.getPlano() != null ? novoAluno.getPlano().getNome() : null,
            novoAluno.getTreino().stream()
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
                )).toList(),
            novoAluno.getPagamento().stream()
                .map(p -> new com.prova.academy.controller.DTO.PagamentoResponseDTO(
                    p.getId(),
                    p.getValor(),
                    p.getDataPagamento().toString(),
                    p.getStatus().name(),
                    p.getAluno().getId()
                )).toList(),
            novoAluno.getAtivo()
        );
    }

    public AlunoResponseDTO atualizarDados(Long id, AlunoDTO alunoDTO) {
        Aluno atualizarAluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        atualizarAluno.setNome(alunoDTO.nome());
        atualizarAluno.setEmail(alunoDTO.email());
        atualizarAluno.setCpf(alunoDTO.cpf());
        atualizarAluno.setDataNascimento(alunoDTO.dataNascimento());
        atualizarAluno.setPlano(planoRepository.findById(alunoDTO.planoId()).get());
        atualizarAluno.setTreino(treinoRepository.findAllById(alunoDTO.treinoId()));

        alunoRepository.save(atualizarAluno);

        pagamentoService.atualizar(atualizarAluno.getId(), new PagamentoDTO(
            atualizarAluno.getPlano().getValorMensal(),
            atualizarAluno.getId()
        ));

        return new AlunoResponseDTO(
            atualizarAluno.getId(),
            atualizarAluno.getNome(),
            atualizarAluno.getEmail(),
            atualizarAluno.getCpf(),
            atualizarAluno.getDataNascimento() != null ? atualizarAluno.getDataNascimento().toString() : null,
            atualizarAluno.getDataCadastro() != null ? atualizarAluno.getDataCadastro().toString() : null,
            atualizarAluno.getPlano() != null ? atualizarAluno.getPlano().getNome() : null,
            atualizarAluno.getTreino().stream()
                    .map(treino -> new TreinoResponseDTO(
                        treino.getId(),
                        treino.getNome(),
                        treino.getExercicios().stream()
                            .map(exercicio -> new com.prova.academy.controller.DTO.ExercicioResponseDTO(
                                exercicio.getId(),
                                exercicio.getDia(),
                                exercicio.getDescricao()
                            ))
                            .toList(),
                        treino.getNivel()
                    )).toList(),
            atualizarAluno.getPagamento().stream()
                .map(p -> new com.prova.academy.controller.DTO.PagamentoResponseDTO(
                    p.getId(),
                    p.getValor(),
                    p.getDataPagamento().toString(),
                    p.getStatus().name(),
                    p.getAluno().getId()
                )).toList(),
            atualizarAluno.getAtivo()
        );
    }

    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }

    public AlunoResponseDTO reativar(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        if(aluno.getPagamento().stream().filter(p -> p.getStatus().name().equals("PENDENTE") || p.getStatus().name().equals("ATRASADO")).toList().size() > 0) {
            throw new RuntimeException("Aluno possui pagamentos pendentes ou atrasados");
        }

        aluno.setAtivo(true);
        alunoRepository.save(aluno);

        return new AlunoResponseDTO(
            aluno.getId(),
            aluno.getNome(),
            aluno.getEmail(),
            aluno.getCpf(),
            aluno.getDataNascimento() != null ? aluno.getDataNascimento().toString() : null,
            aluno.getDataCadastro() != null ? aluno.getDataCadastro().toString() : null,
            aluno.getPlano() != null ? aluno.getPlano().getNome() : null,
            aluno.getTreino().stream()
                    .map(treino -> new TreinoResponseDTO(
                        treino.getId(),
                        treino.getNome(),
                        treino.getExercicios().stream()
                            .map(exercicio -> new com.prova.academy.controller.DTO.ExercicioResponseDTO(
                                exercicio.getId(),
                                exercicio.getDia(),
                                exercicio.getDescricao()
                            ))
                            .toList(),
                        treino.getNivel()
                    )).toList(),
            aluno.getPagamento().stream()
                .map(p -> new com.prova.academy.controller.DTO.PagamentoResponseDTO(
                    p.getId(),
                    p.getValor(),
                    p.getDataPagamento().toString(),
                    p.getStatus().name(),
                    p.getAluno().getId()
                )).toList(),
            aluno.getAtivo()
        );
    }

    public AlunoResponseDTO inativar(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        if(aluno.getPagamento().stream().filter(p -> p.getStatus().name().equals("PENDENTE") || p.getStatus().name().equals("ATRASADO")).toList().size() > 0) {
            throw new RuntimeException("Aluno possui pagamentos pendentes ou atrasados");
        }

        aluno.setAtivo(false);
        alunoRepository.save(aluno);
        
        return new AlunoResponseDTO(
            aluno.getId(),
            aluno.getNome(),
            aluno.getEmail(),
            aluno.getCpf(),
            aluno.getDataNascimento() != null ? aluno.getDataNascimento().toString() : null,
            aluno.getDataCadastro() != null ? aluno.getDataCadastro().toString() : null,
            aluno.getPlano() != null ? aluno.getPlano().getNome() : null,
            aluno.getTreino().stream()
                    .map(treino -> new TreinoResponseDTO(
                        treino.getId(),
                        treino.getNome(),
                        treino.getExercicios().stream()
                            .map(exercicio -> new com.prova.academy.controller.DTO.ExercicioResponseDTO(
                                exercicio.getId(),
                                exercicio.getDia(),
                                exercicio.getDescricao()
                            ))
                            .toList(),
                        treino.getNivel()
                    )).toList(),
            aluno.getPagamento().stream()
                .map(p -> new com.prova.academy.controller.DTO.PagamentoResponseDTO(
                    p.getId(),
                    p.getValor(),
                    p.getDataPagamento().toString(),
                    p.getStatus().name(),
                    p.getAluno().getId()
                )).toList(),
            aluno.getAtivo()
        );
    }
}
