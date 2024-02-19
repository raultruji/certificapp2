package com.raultruji.certificapp2.application.services;

import java.util.List;
import java.util.Optional;

import com.raultruji.certificapp2.application.dto.UserDTO;


public interface IUserService {

	UserDTO createUser(UserDTO userDTO);
	Optional<UserDTO> getUserById(Long id);
	List<UserDTO> findAllUsers();
	Optional<UserDTO> getUserByUsuario(String nombre);
	
}
