package com.raultruji.certificapp2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raultruji.certificapp2.application.dto.UserDTO;
import com.raultruji.certificapp2.domain.models.User;

public interface IUserRepository extends JpaRepository<User, Long>{

	List<User> findAll();
	Optional<User> findUserById(Long idUsuario);	
	
	//query method para buscar por user name (ususario)
	Optional<User> findUserByUsuario(String usuario);

	
}
