package com.comeia.itask.model.service;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comeia.itask.model.domain.Tarefa;
import com.comeia.itask.model.repository.TarefaRepository;

@Service
public class TarefaService {
	@Autowired
	private TarefaRepository repository;

	@Transactional(readOnly = false)
	public void salvarTarefa(Tarefa tarefa) {
		repository.save(tarefa);
	}

	public List<Tarefa> buscarTodas() {
		return repository.findByOrderByCreatedTimeDesc();
	}

	@Transactional(readOnly = false)
	public void excluir(UUID id) {
		repository.deleteById(id);
	}

	public Page<Tarefa> buscarPorData(int tamanhoPaginas, int numeroPagina, String ordem, LocalDate dataInicio) {

		Pageable pg = PageRequest.of(numeroPagina, tamanhoPaginas,
				(ordem.equals("asc") ? Direction.ASC : Direction.DESC), "dataInicio");
		return repository.findByDataInicioOrderByCreatedTimeAsc(pg, dataInicio);
	}

	public Page<Tarefa> buscarPorResponsavel(int tamanhoPaginas, int numeroPagina, String ordem, String responsavel) {
		Pageable pg = PageRequest.of(numeroPagina, tamanhoPaginas,
				(ordem.equals("asc") ? Direction.ASC : Direction.DESC), "dataInicio");
		return repository.findByResponsavelOrderByCreatedTimeAsc(pg, responsavel);
	}

	public Page<Tarefa> buscarPorStatus(int tamanhoPaginas, int numeroPagina, String ordem, int status) {

		Pageable pg = PageRequest.of(numeroPagina, tamanhoPaginas,
				(ordem.equals("asc") ? Direction.ASC : Direction.DESC), "dataInicio");
		return repository.findByStatus(pg, status);
	}

	public HSSFWorkbook excel() {
		List<Tarefa> tarefas = this.buscarTodas();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Tarefas");
		
		sheet.setDefaultColumnWidth(15);
		
		CellStyle headerStyle = workbook.createCellStyle();
		
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short)10);
		font.setBold(true);
		headerStyle.setFont(font);
		
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setWrapText(true);
		
		Cell cell;
		Row row;
		int linha = 0;
		int celula = 0;
		
		row = sheet.createRow(linha++);
		
		cell = row.createCell(celula++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue("Título");
		
		cell = row.createCell(celula++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue("Descrição");
		
		cell = row.createCell(celula++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue("Responsável");
		
		cell = row.createCell(celula++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue("Data de início");
		
		cell = row.createCell(celula++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue("Status");
		
		cell = row.createCell(celula++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue("Criado em");
		
		row = sheet.createRow(linha++);

		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter df2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		for (Tarefa t : tarefas) {
			row = sheet.createRow(linha++);
			celula = 0;

			cell = row.createCell(celula++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(t.getTitulo());

			cell = row.createCell(celula++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(t.getDescricao());

			cell = row.createCell(celula++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(t.getResponsavel());

			cell = row.createCell(celula++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(df.format(t.getDataInicio()));

			cell = row.createCell(celula++);
			cell.setCellStyle(cellStyle);
			switch (t.getStatus()) {
			case 0:
				cell.setCellValue("Em espera");
				break;
			case 1:
				cell.setCellValue("Em andamento");
				break;
			case 2:
				cell.setCellValue("Concluída");
				break;

			default:
				break;
			}
			
			cell = row.createCell(celula++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(df2.format(t.getCreatedTime()));
		}
		
		
		return workbook;
	}

	@Transactional(readOnly = false)
	public void atualizar(UUID id, Tarefa tarefa) {
		Tarefa t = repository.getOne(id);
		
		t.setDescricao(tarefa.getDescricao());
		t.setDataInicio(tarefa.getDataInicio());
		t.setResponsavel(tarefa.getResponsavel());
		t.setStatus(tarefa.getStatus());
		t.setTitulo(tarefa.getTitulo());
	}
}
