package com.raultruji.certificapp2.domain.models;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserDetailModel  implements UserDetails{

	private User user;
	private String usuario;
	private String password;
	private Permiso permiso;
	//private List<GrantedAuthority> authorities;
	private GrantedAuthority authority;
	
	public UserDetailModel(User user) {
		this.user = user;
		this.usuario = user.getUsuario();
		this.password = user.getPassword();
		this.authority = new SimpleGrantedAuthority(user.getPermiso().name().toString());
				
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(user.getPermiso().name()));
	}

	@Override
	public String getPassword() {
		return this.password;	}

	@Override
	public String getUsername() {
		return this.usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getFullName() {
		return user.getNombre()+" "+user.getApellido1();
	}
}
