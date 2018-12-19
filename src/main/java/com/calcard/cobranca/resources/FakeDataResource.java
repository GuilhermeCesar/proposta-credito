package com.calcard.cobranca.resources;

import com.calcard.cobranca.model.Customer;
import com.calcard.cobranca.model.Proposal;
import com.calcard.cobranca.repository.CustomerRepository;
import com.calcard.cobranca.repository.ProposalRepository;
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

    @CrossOrigin
    @GetMapping
    public String createFakeData(){
        Customer customer = new Customer("Guilherme César Medeiros","08700209945");
        customer.setAge(15);
        customer.setCivilStatus("Solteiro");

        customerRepository.save(customer);
        proposalRepository.save(new Proposal(customer));

        customer = new Customer("Bruno","0870014578");
        customer.setAge(15);
        customer.setCivilStatus("Solteiro");

        customerRepository.save(customer);
        proposalRepository.save(new Proposal(customer));

        return "Agora você tem dados!";
    }
}
