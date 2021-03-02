package com.comeia.itask.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@SuppressWarnings("serial")
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable{

	@Getter
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(columnDefinition = "char(36)", unique = true, updatable = false)
	@Type(type = "uuid-char")
	private UUID id;
	
	@Getter
	@CreatedDate
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss", iso = ISO.DATE_TIME)
	private LocalDateTime createdTime;
	
	@Getter
	@LastModifiedDate
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss", iso = ISO.DATE_TIME)
	private LocalDateTime lastModifiedTime;
	
	

}
