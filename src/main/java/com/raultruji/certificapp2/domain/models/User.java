package com.raultruji.certificapp2.domain.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.java.Log;

@Entity
@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario"})})
@Data
//To get the MappedSuperclass attributes to build the builder
@SuperBuilder
@Builder
@Log
@EqualsAndHashCode(callSuper=false)
//@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User extends PersonaBase implements Serializable, UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "id_usuario", nullable = false)
	private long id;
	@NotBlank(message = "User Name is mandatory")
	@Column(name = "usuario", nullable = false)
	private String usuario;
	//https://www.youtube.com/watch?v=hmQdrRvXrGk&t=100s encriptar con BCrypt class
	//User impl -> save method -> user.setPassword (Bcrypt.hashpw(user.getPass(), BCrypt.gensalt()))
	private String password;	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "permiso", columnDefinition = "ENUM('USER','TEST','ADMIN','GUEST') default 'USER'")
	private Permiso permiso;
	
	@OneToMany(mappedBy = "idCliente",fetch = FetchType.LAZY)
	//@OrderBy(value = "id")
	private List<Cliente> clientes;
	
	@OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = false)
	private List<Certificado> certificados;
		
	@OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "id_direccion")
    private Direccion address;
	
	
	//Metodos  UserUtils para validar usuario
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//lista con el obj que representa la auth otorgada al usuario
		return List.of(new SimpleGrantedAuthority(permiso.name()));
	}


	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}



}
