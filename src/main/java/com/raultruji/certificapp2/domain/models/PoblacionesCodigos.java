package com.raultruji.certificapp2.domain.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class PoblacionesCodigos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String paisCorto;
	private String pais;
	private int codigoPostal;
	private String poblacion;
	private String CAutonoma;
	private String lat;
	private String lng;
	
}
