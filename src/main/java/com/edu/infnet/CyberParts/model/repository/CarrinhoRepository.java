package com.edu.infnet.CyberParts.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edu.infnet.CyberParts.model.domain.Carrinho;

@Repository
public interface CarrinhoRepository extends CrudRepository<Carrinho, Integer>{

}
