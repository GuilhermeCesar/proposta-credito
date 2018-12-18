package com.calcard.cobranca.resources;
import com.calcard.cobranca.model.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/cobranca")
public class CobrancaResouce {

    @GetMapping(produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> cobrancas(){
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
