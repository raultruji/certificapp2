package com.raultruji.certificapp2.infrastructure.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.raultruji.certificapp2.application.dto.UserLoginDTO;

@Controller
public class LoginController {
	
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/certificapp/login")
	public String getLoginPage() {
		return "login";
	}

	@PostMapping("/certificapp/login")
	public String authenticateUser(@RequestBody UserLoginDTO loginDto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsuario(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/certificapp/main_menu";
	}
}
