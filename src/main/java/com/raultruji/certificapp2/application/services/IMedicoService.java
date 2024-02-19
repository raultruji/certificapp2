package com.raultruji.certificapp2.application.services;

import com.raultruji.certificapp2.application.dto.MedicoDTO;

public interface IMedicoService {
	public MedicoDTO getById(Long id);	
	public MedicoDTO save (MedicoDTO medico);		
	public void delete(MedicoDTO medico);
}
