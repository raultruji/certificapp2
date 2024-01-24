package com.raultruji.certificapp2.application.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AuditDTO implements Serializable{

	 private static final long serialVersionUID = 1L;

	private LocalDateTime createdOn;

	 private LocalDateTime updatedOn;
}
