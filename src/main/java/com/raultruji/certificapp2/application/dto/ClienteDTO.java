package com.raultruji.certificapp2.application.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idCliente;
	private UserDTO user;
	private PersonaDTO persona;
}
