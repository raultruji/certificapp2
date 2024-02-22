package com.raultruji.certificapp2.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.raultruji.certificapp2.domain.models.Certificado;
import com.raultruji.certificapp2.domain.models.Cliente;
import com.raultruji.certificapp2.domain.models.Direccion;
import com.raultruji.certificapp2.domain.models.Permiso;
import com.raultruji.certificapp2.domain.models.User;

import lombok.Data;
@Data
public class RegistrationForm {

	private String usuario;
	private String name;
	private String ape1;
	private String ape2;
	private String password;
	private String email;
	
	public User toUser(PasswordEncoder encoder) {
		List<Cliente> clients = new ArrayList<>();
		List<Certificado> certis = new ArrayList<>();
		User user = new User();
		user.setUsuario(usuario);
		user.setNombre(name);
		user.setApellido1(ape1);
		user.setApellido2(ape2);
		user.setPassword(encoder.encode(password));
		user.setEmail(email);
		user.setClientes(clients);
		user.setCertificados(certis);
		return user;
	}
}
