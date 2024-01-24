package com.raultruji.certificapp2.application.dto;

import java.io.Serializable;

import com.raultruji.certificapp2.domain.models.PersonaBase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idMedico;
	private Integer numColegiado;
	private String provColegio;
	private String especialidad;
	private boolean activo;
	
	private PersonaBase persona;
	
	/*
    private Long id_persona;	
	private String nombre;
	private String apellido1;
	private String apellido2;
	private LocalDateTime nacimiento;
	private String email;
	
	private AuditDTO audit;
	private Identificacion identificacion;
	*/
	
	
}
