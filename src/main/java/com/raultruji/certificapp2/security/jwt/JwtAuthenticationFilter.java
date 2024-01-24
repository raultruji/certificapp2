package com.raultruji.certificapp2.security.jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
/**
 * OncePerRequestFilter: El filtro se aplica 1 vez por request
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//obtener el token
		final String token = getTokenFromRequest(request);
		final String username;
		
		if(token==null) { //token nulo: se devuelve el control a la cadena de filtros
			filterChain.doFilter(request, response);
			return;
		}
		
		username = jwtService.getUsernameFromToken(token);
		
		//Si username no es nulo y no lo podemos encontrar en el contextHolder, lo buscamos en la bdd 
		if (username!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			// y validamos que el token sea valido
			if(jwtService.isTokenValid(token,userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						 userDetails,
						 null, //credenciales
						 userDetails.getAuthorities());
						
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
			
		}
		filterChain.doFilter(request, response);
		
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		//1o recuperamos el header del token
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		//verificamos esto para retornar el token ( que viene despues de "Bearer")
		if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}
		return null;
	}

}
