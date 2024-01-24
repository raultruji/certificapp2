package com.raultruji.certificapp2.domain.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Locale;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Entity
@Table(name = "certificados")
@Data
//@EqualsAndHashCode(callSuper=true)
@Log
@NoArgsConstructor
@AllArgsConstructor

public class Certificado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotBlank
	@Column(name = "id_certificado", nullable = false)
	private Long idCertificado;
	@Column(name = "importe", columnDefinition = "DECIMAL(5,2) default '15.00'")
	private double importe;
	@Column(name = "oficial", columnDefinition = "TINYINT(1) default '1'")
	private boolean oficial;
	// @NotBlank
	@Column(name = "texto", columnDefinition = "VARCHAR(500) default ''")
	private String texto;
	// TODO en principio JPA lo transforma en VARCHAR en la BDD, en ppio...
	@Column(name = "locale", columnDefinition = "VARCHAR(15) default 'es_Latn'")
	private Locale locale;
	@Column(name = "lugar", columnDefinition = "VARCHAR(25) default 'Barcelona'")
	private String lugar;
	@Column(name = "sex", columnDefinition = "ENUM('X','V','M') default 'X'")
	private Sex sex;
	@Embedded
	private Audit audit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="usuario")
	private User usuario;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="medico")
	private Medico medico;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="cliente")
	private Cliente cliente;
	
	
	
	
	@PrePersist
	public void fillCreatedOn() {
		log.info("Attempting to add new certificado with id: " + this.idCertificado);
		audit.setCreatedOn(LocalDateTime.now());
	}

	@PreUpdate
	public void fillUpdatedOn() {
		audit.setUpdatedOn(LocalDateTime.now());
	}
	
}
