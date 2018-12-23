package com.calcard.cobranca.resources;

import com.calcard.cobranca.dto.CustomerDto;
import com.calcard.cobranca.exception.ProposalNotExistException;
import com.calcard.cobranca.model.Proposal;
import com.calcard.cobranca.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/proposals")
public class ProposalResource {

    @Autowired
    private ProposalService proposalService;

    @GetMapping
    public @ResponseBody ResponseEntity<?> searchAllProposals(){
        try{
            Iterable<Proposal> proposals = proposalService.searchAllProposals();
            return new ResponseEntity<>(proposals,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Erro no servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/{socialId}")
    public @ResponseBody ResponseEntity<?> searchProposalById(@PathVariable("socialId") String socialId){
        try {
            Iterable<Proposal> proposals = this.proposalService.searchProposalBySocialId(socialId);
            return new ResponseEntity<>(proposals, HttpStatus.OK);
        }catch (ProposalNotExistException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>("Erro no servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public @ResponseBody ResponseEntity<?> createProposal(@RequestBody CustomerDto customerDto){
        try {
            Proposal proposal = this.proposalService.createProposal(customerDto);
            return new ResponseEntity<>(proposal,HttpStatus.CREATED);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>("Erro ao criar o cliente",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
