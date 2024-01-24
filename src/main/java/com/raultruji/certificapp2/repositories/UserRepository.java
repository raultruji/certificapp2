package com.raultruji.certificapp2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raultruji.certificapp2.domain.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

	List<User> findAll();
	User getUserByIdUsuario(Long idUsuario);	
	
	//query method para buscar por user name (ususario)
	Optional<User> findByusuario(String usuario);
	
}
