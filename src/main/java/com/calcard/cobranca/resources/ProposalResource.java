package com.calcard.cobranca.resources;

import com.calcard.cobranca.model.Proposal;
import com.calcard.cobranca.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/proposals")
public class ProposalResource {

    @Autowired
    private ProposalService proposalService;

    @GetMapping(produces = "application/json")
    public @ResponseBody ResponseEntity<?> cobrancas(){
        Iterable<Proposal> proposals = proposalService.searchAllCostumers();

        return new ResponseEntity<>(proposals,HttpStatus.OK);
    }


}
