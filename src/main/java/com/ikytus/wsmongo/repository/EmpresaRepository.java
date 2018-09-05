package com.ikytus.wsmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.wsmongo.domain.Empresa;

@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, String> {
	
}
