package com.ikytus.prysma.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.prysma.domain.Empresa;

@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, String> {
			
	List<Empresa> findByMatrizId (String id);
	
	
}
