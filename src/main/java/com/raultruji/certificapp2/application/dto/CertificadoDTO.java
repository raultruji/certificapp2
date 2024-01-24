package com.raultruji.certificapp2.application.dto;

import java.io.Serializable;
import java.util.Locale;

import com.raultruji.certificapp2.domain.models.Sex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificadoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id_certificado;
	private double importe;
	private boolean oficial;
	private String texto;
	private Locale locale;
	private Sex sex;
	
	private AuditDTO auditDTO;
	
	private UserDTO user;
	private MedicoDTO medico;
	private ClienteDTO cliente;
	
}
