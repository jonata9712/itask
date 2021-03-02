package com.comeia.itask.model.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Tarefa extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8749175020736392336L;

	@Column(nullable = false)
	@NotBlank
	@Getter
	@Setter
	private String titulo;

	@Column(nullable = false)
	@NotBlank
	@Getter
	@Setter
	private String descricao;

	@Column(nullable = false)
	@NotBlank
	@Getter
	@Setter
	private String responsavel;

	@Column(nullable = false)
	@NotNull
	@Getter
	@Setter
	private LocalDate dataInicio;

	
	/*
	 * 0 para em espera
	 * 1 para em andamento
	 * 2 para concluida
	 */
	@Column(nullable = false)
	@NotNull
	@Getter
	@Setter
	private int status;

	public Tarefa() {
		super();
	}

	@Override
	public String toString() {
		return "Tarefa [titulo=" + titulo + ", descricao=" + descricao + ", responsavel=" + responsavel
				+ ", dataInicio=" + dataInicio + ", status=" + status + "]";
	}

}
