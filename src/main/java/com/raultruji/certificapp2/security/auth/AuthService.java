package com.raultruji.certificapp2.security.auth;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.raultruji.certificapp2.domain.models.User;
import com.raultruji.certificapp2.repositories.UserRepository;
import com.raultruji.certificapp2.security.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository repository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	public AuthResponse login(LoginRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		UserDetails user = repository.findByusuario(request.getUserName()).orElseThrow();
		String token = jwtService.getToken(user);
		return AuthResponse.builder()
				.token(token)
				.build();
	}

	public AuthResponse register(RegisterRequest request) {
		User user= User.builder()
				.usuario(request.getUsuario())
				.password(passwordEncoder.encode(request.getPassword()))
				.permiso(request.getPermiso())
				.address(request.getAddress())
				.nacimiento(request.getNacimiento())
				.email(request.getEmail())	
				.apellido1(request.getApellido1())
				.apellido2(request.getApellido2())
				.nombre(request.getNombre())
				.build()
				;
		repository.save(user);
		
		return AuthResponse.builder()
				.token(jwtService.getToken(user))
				.build();
		
	}

}
