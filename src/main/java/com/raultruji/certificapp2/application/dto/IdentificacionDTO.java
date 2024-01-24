package com.raultruji.certificapp2.application.dto;

import java.io.Serializable;

import com.raultruji.certificapp2.domain.models.TipoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdentificacionDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id_identificacion;
	private String numero;
	private String nacionalidad;
	
	private TipoId tipo;
	
}
