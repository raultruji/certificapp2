package com.raultruji.certificapp2.application.dto;

import java.io.Serializable;

import com.raultruji.certificapp2.domain.models.Permiso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	//private Long id;
	private String usuario;	
	private String password;
	private final Permiso permiso = Permiso.USER;
   
	//form PersonaBase
	private String nombre;
	private String apellido1;
	private String apellido2;

	

	
}
