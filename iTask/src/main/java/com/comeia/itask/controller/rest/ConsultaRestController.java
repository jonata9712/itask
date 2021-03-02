package com.comeia.itask.controller.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comeia.itask.model.domain.Tarefa;
import com.comeia.itask.model.service.TarefaService;

@RestController
@RequestMapping("/tarefas/consulta")
public class ConsultaRestController {

	@Autowired
	private TarefaService service;

	/*
	 * em "tamanhoPaginas" inserir o tamanho desejavel de resultados por página, 
	 * em "numeroPagina" inserir a pagina que deseja consultar, 
	 * em "order" inserir a preferencia de ordenação asc para crescente e desc para decrescente
	 */
	@GetMapping("/data")
	public List<Tarefa> buscarPorData(@RequestParam("tamanhoPaginas") int tamanhoPaginas,
			@RequestParam("numeroPagina") int numeroPagina, @RequestParam("ordem") String ordem,
			@RequestParam("dataInicio") LocalDate dataInicio) {
		return service.buscarPorData(tamanhoPaginas, numeroPagina, ordem, dataInicio).getContent();

	}

	@GetMapping("/reponsavel")
	public List<Tarefa> buscarPorResponsavel(@RequestParam("tamanhoPaginas") int tamanhoPaginas,
			@RequestParam("numeroPagina") int numeroPagina, @RequestParam("ordem") String ordem,
			@RequestParam("responsavel") String responsavel) {
		return service.buscarPorResponsavel(tamanhoPaginas, numeroPagina, ordem, responsavel).getContent();

	}
	
	@GetMapping("/status")
	public List<Tarefa> buscarPorStatus(@RequestParam("tamanhoPaginas") int tamanhoPaginas,
			@RequestParam("numeroPagina") int numeroPagina, @RequestParam("ordem") String ordem,
			@RequestParam("status") int status) {
		return service.buscarPorStatus(tamanhoPaginas, numeroPagina, ordem, status).getContent();

	}
}
