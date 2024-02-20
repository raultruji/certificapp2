package com.raultruji.certificapp2.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.raultruji.certificapp2.domain.models.Certificado;
import com.raultruji.certificapp2.domain.models.Cliente;
import com.raultruji.certificapp2.domain.models.Permiso;
import com.raultruji.certificapp2.domain.models.User;

public class RegistrationForm {

	private String username;
	private String name;
	private String ape1;
	private String ape2;
	private String password;
	private String email;
	
	public User toUser(PasswordEncoder encoder) {
		List<Cliente> clients = new ArrayList<>();
		List<Certificado> certis = new ArrayList<>();
		User user = new User();
		user.setUsuario(username);
		user.setNombre(name);
		user.setApellido1(ape1);
		user.setApellido2(ape2);
		user.setPassword(encoder.encode(password));
		user.setEmail(email);
		return user;
	}
}
