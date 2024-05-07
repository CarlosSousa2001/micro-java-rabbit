package com.crs.propostaweb.repository;

import com.crs.propostaweb.entity.Proposta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long> {
}
