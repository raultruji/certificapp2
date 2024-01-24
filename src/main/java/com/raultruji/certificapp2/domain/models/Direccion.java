package com.raultruji.certificapp2.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Entity
@Table(name = "direcciones")
@Data
@Log
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
/* si es embeddable el id es necesario? TODO
	@Column(name = "id_direccion", nullable = false)
	private Long id_direccion;
*/	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "id_direccion")
	private Long idDireccion;
	@Column(name = "calle", columnDefinition = "varchar(40)")
	private String calle;	
	@Column(name = "codigo_postal",  columnDefinition = "INT default 08000")
	private int codigo_postal;
	@Column(name = "poblacion", columnDefinition = "varchar(40)")
	private String poblacion;
	@Column(name = "provincia", columnDefinition = "varchar(40)")
	private String provincia;
	@Column(name = "numero", columnDefinition = "varchar(7)")
	private String numero;
	@Column(name = "escalera", columnDefinition = "varchar(10)")
	private String escalera;
	@Column(name = "planta", columnDefinition = "varchar(10)")
	private String planta;
	@Column(name = "puerta", columnDefinition = "varchar(10)")
	private String puerta;

	
}
