package com.ikytus.prysma.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.prysma.domain.Produto;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {
	
}
