package com.calcard.cobranca.repository;

import com.calcard.cobranca.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CobrancaRepository extends JpaRepository<Cliente,String> {


}
