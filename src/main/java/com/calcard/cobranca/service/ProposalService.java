package com.calcard.cobranca.service;


import com.calcard.cobranca.exception.ProposalNotExistException;
import com.calcard.cobranca.model.Proposal;
import com.calcard.cobranca.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProposalService {

	@Autowired
	private ProposalRepository  proposalRepository;
	
	public Iterable<Proposal> searchAllProposals(){
		return this.proposalRepository.findAll();
	}

   public Proposal searchProposal(Long id) {
	   Optional<Proposal> proposal = this.proposalRepository.findById(id);

//	   System.out.println("Feito "+proposal.isPresent());

	   proposal.orElseThrow(()->new ProposalNotExistException("Não existe essa Proposta"));

	   return proposal.isPresent()?proposal.get():null;
   }
}
