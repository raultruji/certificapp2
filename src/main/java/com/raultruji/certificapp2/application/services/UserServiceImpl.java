package com.raultruji.certificapp2.application.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.raultruji.certificapp2.application.dto.UserDTO;
import com.raultruji.certificapp2.application.mapper.IUserMapper;
import com.raultruji.certificapp2.domain.models.User;
import com.raultruji.certificapp2.repositories.IUserRepository;

import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@NoArgsConstructor
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserRepository userRepo;
	private PasswordEncoder encoder;
	@Autowired
	private IUserMapper userMapper;
	
	public UserDTO createUser(UserDTO dto) {
		User user = userMapper.userDTOToUser(dto);
		User saved = userRepo.save(user);
		return userMapper.userToUserDTO(saved);
		/*
		user.setNombre(dto.getNombre());
		user.setApellido1(dto.getApellido1());
		user.setApellido2(dto.getApellido2());
		user.setUsuario(dto.getUsuario());
		user.setPermiso(dto.getPermiso());
		user.setPassword(encoder.encode(dto.getPassword()));
		*/		

	}
	
	public Optional<UserDTO> getUserById(Long id) {	
		Optional<User> optional = userRepo.findUserById(id);		
		return Optional.ofNullable(optional.map(userMapper::userToUserDTO).orElse(null));

	}
	@Override
	public  Optional<UserDTO>  getUserByUsuario(String nombre) {
		UserDTO aux = new UserDTO();
		Optional<User> opt = userRepo.findUserByUsuario(nombre);
		return Optional.of(opt.map(userMapper::userToUserDTO).orElse(aux));
	}
	
	@Override
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map((user) -> userMapper.userToUserDTO(user))
                .collect(Collectors.toList());
    }



}
