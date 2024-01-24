package com.raultruji.certificapp2.security.auth;

import java.time.LocalDateTime;

import com.raultruji.certificapp2.domain.models.Direccion;
import com.raultruji.certificapp2.domain.models.Permiso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

	//private Long idUsuario;
	 String usuario;
	 String password;
	 Permiso permiso;
	 String nombre;
	 String apellido1;
	 String apellido2;
	 LocalDateTime nacimiento;
	 String email;
	 Direccion address;
	
}
