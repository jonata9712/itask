package com.comeia.itask.controller.rest;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comeia.itask.model.domain.Tarefa;
import com.comeia.itask.model.service.TarefaService;

@RestController
@RequestMapping("/tarefas")
public class TarefaRestController {
	@Autowired
	private TarefaService service;
	
	@PostMapping("")
	public ResponseEntity<Object> novaTarefa(@Valid @RequestBody Tarefa tarefa){
		service.salvarTarefa(tarefa);
		return ResponseEntity.status(HttpStatus.OK).body("Salvo com sucesso!");
	}
	
	@GetMapping("")
	public List<Tarefa> listaTarefas(){
		return service.buscarTodas();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> editarTarefa(@PathVariable("id") UUID id, @RequestBody Tarefa tarefa) {
		service.atualizar(id, tarefa);
		return ResponseEntity.status(HttpStatus.OK).body("Atualizado com sucesso!");
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> excluirTarefa(@PathVariable("id") UUID id){
		service.excluir(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Excluído!");
	}
	
	@GetMapping("/xls")
	public void get(HttpServletResponse response) {

		try {
			response.addHeader("Content-Disposition", "attachment; filename=tarefas.xls");
			service.excel().write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
