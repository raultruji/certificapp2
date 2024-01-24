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

	private Long idUsuario;
	private String usuario;
	private String password;
	private Permiso permiso;
}
