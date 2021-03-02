package com.comeia.itask.mvc.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToLocalDateConverter implements Converter<String, LocalDate>{

	@Override
	public LocalDate convert(String source) {
		// TODO Auto-generated method stub
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		return LocalDate.parse(source, df);
	}

}
