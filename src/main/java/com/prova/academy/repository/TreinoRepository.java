package com.prova.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prova.academy.entity.Treino;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Long> {
    Treino findByNome(String nome);
}
