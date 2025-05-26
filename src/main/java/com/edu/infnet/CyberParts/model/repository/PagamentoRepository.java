package com.edu.infnet.CyberParts.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edu.infnet.CyberParts.model.domain.Pagamento;

@Repository
public interface PagamentoRepository extends CrudRepository<Pagamento, Integer>{

}
