package com.raultruji.certificapp2.infrastructure.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/certificapp/login")
public class LoginController {

	@GetMapping()
	public String getLoginPage() {
		return "login";
	}
	
}
