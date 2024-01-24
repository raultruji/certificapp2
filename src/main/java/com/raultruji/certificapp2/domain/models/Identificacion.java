package com.raultruji.certificapp2.domain.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Identificacion {

	private Long id_identificacion;
	private String numero;
	private String nacionalidad;
	private TipoId tipoId;
	//private TipoId tipo;

	private enum Tipo{
		DNI,NIE,NIF,PASAPORTE
	}
}
