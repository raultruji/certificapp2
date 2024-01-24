package com.raultruji.certificapp2.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.raultruji.certificapp2.application.dto.ClienteDTO;
import com.raultruji.certificapp2.domain.models.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
	ClienteMapper INSTANCE = Mappers.getMapper( ClienteMapper.class );
	
	ClienteDTO clienteToDTO(Cliente cliente);
	Cliente DTOToCliente(ClienteDTO cliente);
}
