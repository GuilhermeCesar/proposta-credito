package com.calcard.cobranca.repository;

import com.calcard.cobranca.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CobrancaRepository extends CrudRepository<Cliente,String> {


}
