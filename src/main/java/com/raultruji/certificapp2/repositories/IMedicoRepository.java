package com.raultruji.certificapp2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raultruji.certificapp2.domain.models.Medico;

public interface IMedicoRepository extends JpaRepository<Medico, Long>{

	//Set bc there is no repes
	//Optional<Medico> findById(Long id);
	Optional<Medico> findByNumColegiado(Integer id);
	List<Medico> findAll();
	//Medico isActivo();
	
	
	
}
