package com.comeia.itask.model.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comeia.itask.model.domain.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, UUID>{

	public Page<Tarefa> findByDataInicioOrderByCreatedTimeAsc(Pageable pageable, LocalDate dataInicio);

	public Page<Tarefa> findByResponsavelOrderByCreatedTimeAsc(Pageable pg, String responsavel);

	public Page<Tarefa> findByStatus(Pageable pg, int status);

	public List<Tarefa> findByOrderByCreatedTimeDesc();

}
