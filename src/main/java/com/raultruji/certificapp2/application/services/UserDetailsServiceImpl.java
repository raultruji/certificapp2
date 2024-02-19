package com.raultruji.certificapp2.application.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.raultruji.certificapp2.domain.models.User;
import com.raultruji.certificapp2.domain.models.UserDetailModel;
import com.raultruji.certificapp2.repositories.IUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private IUserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findUserByUsuario((username));
		
		return user.map(UserDetailModel::new).orElseThrow(() -> new UsernameNotFoundException("Usuario invalido"));
	}

}
