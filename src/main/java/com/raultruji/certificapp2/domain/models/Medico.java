package com.raultruji.certificapp2.domain.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Entity
@Table(name = "medicos")
@Data
@EqualsAndHashCode(callSuper=true)
@Log
@NoArgsConstructor
@AllArgsConstructor

public class Medico extends PersonaBase implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotBlank
	@Column(name = "id_medico", nullable = false)
	private Long idMedico;
	
	@NotNull(message = "Num. col. is mandatory")
	@Column(name = "num_colegiado", nullable = false)
	private Integer numColegiado;
	
	@Column(name = "prov_colegio", columnDefinition = "varchar(20) default 'Barcelona'")
	private String provColegio;
	
	@Column(name = "especialidad", columnDefinition = "varchar(40) default 'Medicina General'" )
	private String especialidad;
	
	@Column(name = "activo", columnDefinition = "boolean default true")
	private boolean activo;

	@OneToMany(mappedBy = "medico", orphanRemoval = false)
	private List<Certificado> certificados;
}
