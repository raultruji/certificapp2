package com.raultruji.certificapp2.application.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String calle;	
	private int codigo_postal;
	private String poblacion;
	private String provincia;
	private String numero;
	private String escalera;	
	private String planta;
	private String puerta;
}
