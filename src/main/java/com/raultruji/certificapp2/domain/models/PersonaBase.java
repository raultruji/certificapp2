package com.raultruji.certificapp2.domain.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.java.Log;

@MappedSuperclass
@Setter
@Data
@Log
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public abstract class PersonaBase implements Serializable{
	private static final long serialVersionUID = 1L;
/*
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id_persona;
    */
	
	@Column(name = "nombre",length = 25, nullable = false)
	private String nombre;
	@Column(name = "apellido1",length = 45)
	private String apellido1;
	@Column(name = "apellido2",length = 25)
	private String apellido2;
	@Column(name = "nacimiento")
	private LocalDateTime nacimiento;
	@Column(name = "email")
	@Email
	private String email;

	
	@Embedded  
    private Audit audit;
	
	@Embedded
	private Identificacion identificacion;
	
	
	 @PrePersist
	    public void fillCreatedOn() {
	    	log.info("Attempting to add new Persona with name: " );
	    	 if (this.audit == null) {
	             this.audit = new Audit();
	         }
	         this.audit.setCreatedOn(LocalDateTime.now());
	    }

	    @PreUpdate
	    public void fillUpdatedOn() {
	        this.audit.setUpdatedOn(LocalDateTime.now());
	    }
}
