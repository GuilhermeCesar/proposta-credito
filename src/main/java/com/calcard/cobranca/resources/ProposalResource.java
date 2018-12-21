package com.calcard.cobranca.resources;

import com.calcard.cobranca.Dto.CustomerDto;
import com.calcard.cobranca.exception.ProposalNotExistException;
import com.calcard.cobranca.model.Proposal;
import com.calcard.cobranca.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/proposals")
public class ProposalResource {

    @Autowired
    private ProposalService proposalService;

    @CrossOrigin
    @GetMapping
    public @ResponseBody ResponseEntity<?> searchAllProposals(){
        try{
            Iterable<Proposal> proposals = proposalService.searchAllProposals();
            return new ResponseEntity<>(proposals,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Erro no servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public @ResponseBody ResponseEntity<?> searchProposalById(@PathVariable("id") Long id){
        try {
            Proposal proposal = this.proposalService.searchProposal(id);
            return new ResponseEntity<>(proposal, HttpStatus.OK);
        }catch (ProposalNotExistException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>("Erro no servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping
    public @ResponseBody ResponseEntity<?> createProposal(@ModelAttribute CustomerDto customerDto){
        try {
            this.proposalService.createProposal(customerDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>("Erro ao criar o cliente",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
