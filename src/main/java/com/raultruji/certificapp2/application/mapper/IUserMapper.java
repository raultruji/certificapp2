package com.raultruji.certificapp2.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.factory.Mappers;

import com.raultruji.certificapp2.application.dto.UserDTO;
import com.raultruji.certificapp2.domain.models.User;

@Mapper(componentModel = "spring")
public interface IUserMapper {

	//IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

	@Mapping(target = "clientes", ignore = true)
	@Mapping(target = "certificados", ignore = true)
	@Mapping(target = "address", ignore = true)
	@Mapping(target = "nacimiento", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "audit", ignore = true)
	//@Named("userDtoToEntity")
	User userDTOToUser(UserDTO dto);

	//@Named("userToDto")
	UserDTO userToUserDTO(User user);
}
