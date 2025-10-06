package com.prova.academy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prova.academy.entity.Pagamento;
import com.prova.academy.entity.StatusPagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByAlunoId(Long alunoId);
    List<Pagamento> findByStatus(StatusPagamento status);
}
