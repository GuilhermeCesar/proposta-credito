package com.calcard.cobranca.service;


import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import com.calcard.cobranca.model.*;

@Service
public class PropostaService {
	
	public List<Cliente> buscaPropostas(){
		Cliente cliente = new Cliente();
		cliente.setNome("Guilherme");
		
		Cliente cliente2 = new Cliente();
		cliente2.setNome("Guilherme 2");
		
		return Arrays.asList(cliente,cliente2);		
	}

    public static class ServiceConfiguration {
    }
}
