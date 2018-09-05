package com.ikytus.wsmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.wsmongo.domain.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {
	
}
