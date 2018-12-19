package com.calcard.cobranca.service;


import com.calcard.cobranca.model.Proposal;
import com.calcard.cobranca.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProposalService {

	@Autowired
	private ProposalRepository  proposalRepository;
	
	public Iterable<Proposal> searchAllCostumers(){
		return this.proposalRepository.findAll();
	}

    public static class ServiceConfiguration {
    }
}
