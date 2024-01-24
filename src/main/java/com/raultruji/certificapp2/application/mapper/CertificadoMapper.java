package com.raultruji.certificapp2.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.raultruji.certificapp2.application.dto.CertificadoDTO;
import com.raultruji.certificapp2.domain.models.Certificado;

@Mapper(componentModel = "spring")
public interface CertificadoMapper {
	CertificadoMapper INSTANCE = Mappers.getMapper( CertificadoMapper.class );
	
	CertificadoDTO certificadoToDTO(Certificado certificado);
	Certificado DTOToCertificado(CertificadoDTO certificado);
}
