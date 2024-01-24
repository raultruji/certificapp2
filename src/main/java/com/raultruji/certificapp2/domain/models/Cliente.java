package com.raultruji.certificapp2.domain.models;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Entity
@Table(name = "clientes")
@Data
@EqualsAndHashCode(callSuper=true)
@Log
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends PersonaBase implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente", nullable = false)
	private Long idCliente;
		
	@OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "id_direccion")
    private Direccion address;
	
	@JoinColumn(name = "user", nullable = false)
	@ManyToOne(optional = false)
	private User user;

}
