package com.raultruji.certificapp2.infrastructure.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.raultruji.certificapp2.application.dto.UserDTO;
import com.raultruji.certificapp2.application.services.IUserService;

@Controller
@RequestMapping("/certificapp/register")
public class RegisterController {
	@Autowired
	private IUserService userService;
	
	@GetMapping
	public String getRegisterPage(Model model) {
		UserDTO user = new UserDTO();
		model.addAttribute("user",user);
		return "register";
	}
	@PostMapping
	public String registerUser(@ModelAttribute ("user") UserDTO userDTO,
			Model model, BindingResult result) {
		String success= "error registring";
		Optional<UserDTO> userExists = userService.getUserByUsuario(userDTO.getUsuario());
		if(!userExists.isEmpty() && userExists.get().getUsuario()!=null) {
			result.rejectValue("user", null, "Ya existe ese nombre de usuario");
			return "redirect:/certificapp/register?success=" + success;
		}
		if(result.hasErrors()) {
			model.addAttribute("user", userDTO);
			return "redirect:/certificapp/register?success=" + success;
		}
		userService.createUser(userDTO);
		System.out.println("Success en el register");
		success="success registring";
		return "redirect:/certificapp/register?success=" + success;
	}
	
	@ModelAttribute
	public void createUser(Model model) {
		UserDTO user = new UserDTO();
		model.addAttribute("user",user);
	}
	
	
}
