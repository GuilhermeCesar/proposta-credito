package com.calcard.cobranca.service;


import com.calcard.cobranca.Dto.CustomerDto;
import com.calcard.cobranca.exception.ProposalNotExistException;
import com.calcard.cobranca.model.CivilStatus;
import com.calcard.cobranca.model.Customer;
import com.calcard.cobranca.model.Proposal;
import com.calcard.cobranca.model.Status;
import com.calcard.cobranca.repository.CustomerRepository;
import com.calcard.cobranca.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
public class ProposalService {

	@Autowired
	private ProposalRepository  proposalRepository;
	@Autowired
	private CustomerRepository customerRepository;


	public Iterable<Proposal> searchAllProposals(){
		return this.proposalRepository.findAll();
	}

   	public Proposal searchProposal(Long id) {
	   Optional<Proposal> proposal = this.proposalRepository.findById(id);

	   proposal.orElseThrow(()->new ProposalNotExistException("Não existe essa Proposta"));

	   return proposal.get();
   	}


   	private CivilStatus getCivilStatus(String civilStatus){
		switch (civilStatus.toUpperCase()){
			case "MARRIED":
				return CivilStatus.MARRIED;
			case "SINGLE":
				return CivilStatus.SINGLE;
			case "DIVORCED":
				return CivilStatus.DIVORCED;
			case "WIDOWER":
				return CivilStatus.WIDOWER;
			default:
				return CivilStatus.SINGLE;
		}
	}

   	public Proposal createProposal(CustomerDto customerDto){
		Customer customer = new Customer(customerDto.getFullName(), customerDto.getSocialId());
		customer.setAge(customerDto.getAge());
		customer.setCivilStatus(this.getCivilStatus(customerDto.getCivilStatus()));
		customer.setDependents(customerDto.getDependents());
		customer.setState(customerDto.getState());
		customer.setSalary(new BigDecimal(customerDto.getSalary()));
		customer.setGener(customerDto.getGener());

		this.customerRepository.save(customer);

		Proposal proposal = this.analizeProposal(customer);

		this.proposalRepository.save(proposal);

		return proposal;
	}

	private BigDecimal calcSalaryPerPerson(Integer dependents, BigDecimal salary){
		return salary.divide(new BigDecimal(dependents),2, BigDecimal.ROUND_HALF_UP);
	}

	private Boolean isLowIncomePerPerson(Customer customer){
		BigDecimal salaryPerPerson = this
				.calcSalaryPerPerson(customer.getDependents(),customer.getSalary());

		return salaryPerPerson.compareTo(new BigDecimal("1000")) < 0;
	}

	private Boolean isDivorcedOrWidowed(Customer customer){
		return customer.getCivilStatus().equals(CivilStatus.DIVORCED)
					||
				customer.getCivilStatus().equals(CivilStatus.WIDOWER);
	}


	private Proposal isMarried(Customer customer){
		BigDecimal salaryPerPerson = this.calcSalaryPerPerson(customer.getDependents(),customer.getSalary());
		Proposal proposal = new Proposal(new Date(),customer);
		proposal.setStatus(Status.APPROVED);

		if(this.salaryLessThan(salaryPerPerson,"2500")){
			proposal.setMaxLimit(new BigDecimal("1500"));
			proposal.setMinLimit(new BigDecimal("1000"));
			return proposal;
		}

		if(this.salaryLessThan(salaryPerPerson,"4000")){
			proposal.setMaxLimit(new BigDecimal("1500"));
			proposal.setMinLimit(new BigDecimal("2000"));
			return proposal;
		}

		proposal.setMinLimit(new BigDecimal("2000"));
		return proposal;
	}

	private Boolean salaryLessThan(BigDecimal salary, String lessThan){
		return  salary.compareTo(new BigDecimal(lessThan)) < 0;
	}

	private Proposal isSingle(Customer customer){
		BigDecimal salaryPerPerson = this.calcSalaryPerPerson(customer.getDependents(),customer.getSalary());
		Proposal proposal = new Proposal(new Date(),customer);
		proposal.setStatus(Status.APPROVED);

		if(this.salaryLessThan(salaryPerPerson,"2500")){
			proposal.setMaxLimit(new BigDecimal("500"));
			proposal.setMinLimit(new BigDecimal("100"));

			return proposal;
		}

		proposal.setMaxLimit(new BigDecimal("1000"));
		proposal.setMinLimit(new BigDecimal("500"));

		return proposal;
	}

	private Proposal analizeProposal(Customer customer){
		Proposal proposal = new Proposal(customer);

		if(this.isLowIncomePerPerson(customer)){
			proposal.setDisapprovedAt(new Date());
			proposal.setReasonDisaproved("Renda Baixa");
			proposal.setStatus(Status.DISAPPROVED);
			return proposal;
		}

		if(this.isDivorcedOrWidowed(customer)){
			proposal.setDisapprovedAt(new Date());
			proposal.setReasonDisaproved("Reprovado pela politica de crédito");
			return proposal;
		}

		return customer.getCivilStatus().equals(CivilStatus.MARRIED)?
				this.isMarried(customer):this.isSingle(customer);
	}


}
