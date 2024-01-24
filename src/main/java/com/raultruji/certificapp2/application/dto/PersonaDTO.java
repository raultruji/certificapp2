package com.raultruji.certificapp2.application.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    
	private Long id_persona;	
	private String nombre;
	private String apellido1;
	private String apellido2;
	private LocalDateTime nacimiento;
	private String email;
	
	private DireccionDTO direccion;
	
	private AuditDTO audit;
	private IdentificacionDTO identificacion;
	
}
