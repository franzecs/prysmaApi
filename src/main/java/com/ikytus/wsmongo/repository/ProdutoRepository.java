package com.ikytus.wsmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.wsmongo.domain.Produto;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {
	
}
