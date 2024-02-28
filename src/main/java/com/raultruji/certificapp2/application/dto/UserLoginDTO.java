package com.raultruji.certificapp2.application.dto;

import com.raultruji.certificapp2.domain.models.Permiso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

	private String usuario;	
	private String password;
	private final Permiso permiso = Permiso.USER;
}
