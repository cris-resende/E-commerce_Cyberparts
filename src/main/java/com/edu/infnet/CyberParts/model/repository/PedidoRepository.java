package com.edu.infnet.CyberParts.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edu.infnet.CyberParts.model.domain.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Integer>{

}
