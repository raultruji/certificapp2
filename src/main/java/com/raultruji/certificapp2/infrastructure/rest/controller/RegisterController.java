package com.raultruji.certificapp2.infrastructure.rest.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.raultruji.certificapp2.application.dto.UserDTO;
import com.raultruji.certificapp2.application.services.IUserService;
import com.raultruji.certificapp2.domain.models.User;
import com.raultruji.certificapp2.repositories.IUserRepository;
import com.raultruji.certificapp2.security.config.RegistrationForm;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/certificapp/register")
public class RegisterController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserRepository userRepo;
	@Autowired
	private PasswordEncoder encoder;
	
	@GetMapping
	public String getRegisterPage(Model model) {
		UserDTO user = new UserDTO();
		model.addAttribute("user",user);
		return "register";
	}
/*
	@PostMapping
	public String registerUser(@ModelAttribute ("user") UserDTO userDTO, BindingResult result,
			Model model) {
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
	*/
	/*
	@PostMapping
	public String registerUser(@RequestBody("user") Model model, BindingResult result, RegistrationForm form) {
		String success = "error registring";
		Optional<UserDTO> userExists = userService.getUserByUsuario(form.getUsuario());
		
		if(!userExists.isEmpty() && userExists.get().getUsuario()!=null) {
			result.rejectValue("user", null, "Ya existe ese nombre de usuario");
			return "redirect:/certificapp/register?success=" + success;
		}
		if(result.hasErrors()) {			
			return "redirect:/certificapp/register?success=" + success;
		}
		
		User user = form.toUser(encoder);
		userRepo.save(user);
		return "redirect:/login";
	}
	*/
	@PostMapping
    public String registerUser(@Valid User user, BindingResult result, Model model) { 
    	String success = "error in somewhere";
    	Optional<UserDTO> userExists = userService.getUserById(user.getId());
    	if(!userExists.isEmpty() && userExists.get().getUsuario()!=null) {
			result.rejectValue("user", null, "Ya existe ese nombre de usuario");
			return "redirect:/certificapp/register?success=" + success;
		}
		if(result.hasErrors()) {			
			return "redirect:/certificapp/register?success=" + success;
		}
		
		success = "user created succesfully";
    	user.setPassword(encoder.encode(user.getPassword()));
		//problem tipo "java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'sss' for key 'UK3m5n1w5trapxlbo2s42ugwdmd'"
		//TODO FUNCTIONAL but no encoding (
		userRepo.save(user);
    	model.addAttribute("success", success);
        return "redirect:/certificapp/register?success=" + success;
    	
    }

    
    //@PostMapping
    public String processRegistration(RegistrationForm form) {
    userRepo.save(form.toUser(encoder));
    return "redirect:/certificapp/register";
    }
    
	//pa que?
	/*
	@ModelAttribute
	public void createUser(Model model) {
		UserDTO user = new UserDTO();
		model.addAttribute("user",user);
	}
	*/
	
}
