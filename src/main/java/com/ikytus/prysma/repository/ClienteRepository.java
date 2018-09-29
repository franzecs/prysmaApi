package com.ikytus.prysma.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.prysma.domain.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {
		
	Page<Cliente> findByEmpresaId(Pageable pages, String empresaId);
	
	Page<Cliente> findByNomeIgnoreCaseContainingAndEmpresaId(Pageable pages,String nome, String empresaId);
				
	Page<Cliente> findByDataNascimentoBetweenAndEmpresaId(Pageable pages, Date dataInicial, Date dataFinal,String empresaId);
	
	Cliente findByCpf(String cpf);
	
	/*@Query("{ dataNascimento: {$gte: ?0} }, { date: { $lte: ?1} }")
	Page<Cliente> findByAniversario(Date dataInicial, Date dataFinal);*/
}
