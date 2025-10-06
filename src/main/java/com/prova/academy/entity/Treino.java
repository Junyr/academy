package com.prova.academy.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @OneToMany(mappedBy = "treino", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Exercicio> exercicios = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private NivelTreino nivel;

    @ManyToMany(mappedBy = "treinos")
    private List<Aluno> alunos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public NivelTreino getNivel() {
        return nivel;
    }

    public void setNivel(NivelTreino nivel) {
        this.nivel = nivel;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Exercicio> getExercicios() {
        return exercicios;
    }

    public void setExercicios(List<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }

    
}
