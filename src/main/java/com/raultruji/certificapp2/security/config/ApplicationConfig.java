package com.raultruji.certificapp2.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.raultruji.certificapp2.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
/**
 * ApplicationConfig engloba los detalles del Authentication Manager del proveedor
 * y el algoritmo utilizado en el PasswordEncoders, que encripta el pass y lo lleva a la bdd
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

	private final UserRepository userRepository;
	
	//Metodos para acceder a la instancia de Auth manager a partir de auth config
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		
		return config.getAuthenticationManager();
	}
	//Metodo para devolver el proveedor de la autenticacion (auth provider), con el encoder y 
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService userDetailService() {
		return username -> userRepository.findByusuario(username)
				.orElseThrow(()-> new UsernameNotFoundException("Usuario not found"));
	}
}
