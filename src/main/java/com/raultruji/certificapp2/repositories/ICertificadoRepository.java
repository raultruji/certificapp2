package com.raultruji.certificapp2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raultruji.certificapp2.domain.models.Certificado;
import com.raultruji.certificapp2.domain.models.Cliente;
import com.raultruji.certificapp2.domain.models.Medico;
import com.raultruji.certificapp2.domain.models.User;

public interface ICertificadoRepository extends JpaRepository<Certificado, Long>{
	
	List<Certificado> findByIdCertificado(Long id);
	List<Certificado> findByUsuario(User usuario);
	List<Certificado> findByMedico(Medico medico);
	List<Certificado> findByCliente(Cliente cliente);
	List<Certificado> findAll();
	
	
}
