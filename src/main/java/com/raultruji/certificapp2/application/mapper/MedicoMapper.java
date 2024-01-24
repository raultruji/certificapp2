package com.raultruji.certificapp2.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.raultruji.certificapp2.application.dto.MedicoDTO;
import com.raultruji.certificapp2.domain.models.Medico;

@Mapper(componentModel = "spring")
public interface MedicoMapper {
	MedicoMapper INSTANCE = Mappers.getMapper( MedicoMapper.class );
	
	MedicoDTO medicoToDTO(Medico medico);
	Medico DTOToMedico(MedicoDTO medico);
	
}
