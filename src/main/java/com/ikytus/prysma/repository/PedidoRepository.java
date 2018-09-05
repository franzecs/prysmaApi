package com.ikytus.prysma.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.prysma.domain.Pedido;

@Repository
public interface PedidoRepository extends MongoRepository<Pedido, String> {
	
}
