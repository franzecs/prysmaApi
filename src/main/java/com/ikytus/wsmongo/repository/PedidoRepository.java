package com.ikytus.wsmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.wsmongo.domain.Pedido;

@Repository
public interface PedidoRepository extends MongoRepository<Pedido, String> {
	
}
