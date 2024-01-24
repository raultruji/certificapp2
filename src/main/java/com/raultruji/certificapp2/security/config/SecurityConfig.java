package com.raultruji.certificapp2.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.raultruji.certificapp2.security.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;
//Filtro de seguridad

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authProvider;
	@Bean
	public SecurityFilterChain SecurityFilterChain(HttpSecurity http)throws Exception
	{
		return http
				//crossSite Request forgery. Se deshabilita, si no lo andara pidiendo todo el ratooo
			.csrf(csrf ->
					csrf
					.disable())
			.authorizeHttpRequests(authRequest ->
				authRequest
					.requestMatchers("/auth/**").permitAll()
					.anyRequest().authenticated()
					)
			//.formLogin(withDefaults())
			.sessionManagement(sess -> sess
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authProvider)
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.build();				
				
	}			
}
	
