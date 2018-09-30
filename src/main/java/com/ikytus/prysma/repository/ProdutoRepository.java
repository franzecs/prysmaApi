package com.ikytus.prysma.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.prysma.domain.Produto;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {
	
	Page<Produto> findByEmpresaId(Pageable pages, String empresaId);
	
	Page<Produto> findByNomeIgnoreCaseContainingAndEmpresaId(Pageable pages,String nome, String empresaId);
	
}
