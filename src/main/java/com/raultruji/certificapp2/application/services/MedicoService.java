package com.raultruji.certificapp2.application.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raultruji.certificapp2.application.dto.MedicoDTO;
import com.raultruji.certificapp2.application.mapper.MedicoMapper;
import com.raultruji.certificapp2.domain.models.Medico;
import com.raultruji.certificapp2.repositories.MedicoRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@Service
public class MedicoService {

	MedicoRepository repository;
	Validator validator;
	
	@Autowired
	public MedicoService(MedicoRepository repository) {
		this.repository = repository;
	}
	
	public MedicoDTO getById(Long id) {
		MedicoDTO response = null;
		Optional<Medico> medico = repository.findById(id);
		if(medico.isPresent()) {
			response = MedicoMapper.INSTANCE.medicoToDTO(medico.get());
		}
		return response;
	}
	
	public MedicoDTO save (MedicoDTO medico) {
		return saveInformation(medico);
	}
	
	public void delete(MedicoDTO medico){
		
	}
	
	
	/**
	 * DTO to entity -> Validation -> save Entity -> return DTO
	 * @param medico
	 * @return
	 */
	private MedicoDTO saveInformation(MedicoDTO medico) {
		Medico entity = MedicoMapper.INSTANCE.DTOToMedico(medico);
		
		Set<ConstraintViolation<Medico>> violations = validator.validate(entity);
		if(!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
		
		Medico savedMedic = repository.save(entity);

		return MedicoMapper.INSTANCE.medicoToDTO(savedMedic);
	}
}
