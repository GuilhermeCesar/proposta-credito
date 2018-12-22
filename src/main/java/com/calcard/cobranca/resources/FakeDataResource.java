package com.calcard.cobranca.resources;

import com.calcard.cobranca.dto.CustomerDto;
import com.calcard.cobranca.repository.CustomerRepository;
import com.calcard.cobranca.repository.ProposalRepository;
import com.calcard.cobranca.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fakedata")
public class FakeDataResource {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProposalRepository proposalRepository;
    @Autowired
    private ProposalService proposalService;

    @CrossOrigin
    @GetMapping
    public String createFakeData(){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setAge(25);
        customerDto.setCivilStatus("SINGLE");
        customerDto.setDependents(4);
        customerDto.setGener("M");
        customerDto.setSalary("20000");
        customerDto.setSocialId("08700209945");
        customerDto.setState("Santa Catarina");
        customerDto.setFullName("Guilherme César Medeiros");

        this.proposalService.createProposal(customerDto);

        customerDto = new CustomerDto();
        customerDto.setAge(25);
        customerDto.setCivilStatus("DIVORCED");
        customerDto.setDependents(4);
        customerDto.setGener("M");
        customerDto.setSalary("2000");
        customerDto.setSocialId("087001");
        customerDto.setState("Santa Catarina");
        customerDto.setFullName("Bruno");

        this.proposalService.createProposal(customerDto);

        return "Agora você tem dados!";
    }
}
