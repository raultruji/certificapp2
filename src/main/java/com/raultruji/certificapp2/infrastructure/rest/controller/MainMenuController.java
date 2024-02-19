package com.raultruji.certificapp2.infrastructure.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainMenuController {

	@GetMapping("/main_menu")
	public String getLoginPage() {
		return "main_menu";
	}
	
}
