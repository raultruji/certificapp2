package com.raultruji.certificapp2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raultruji.certificapp2.domain.models.Cliente;
import com.raultruji.certificapp2.domain.models.Identificacion;
import com.raultruji.certificapp2.domain.models.User;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Cliente getClienteByIdCliente(Long idCliente);
	/**
	 * Referido al num identificacion (String)
	 * @param numero
	 * @return
	 */
	Cliente getClienteByIdentificacion(Identificacion identificacion);
	List<Cliente> findAll();
	List<Cliente> getClienteByUser(User user);
}
