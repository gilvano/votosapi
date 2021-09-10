package com.gilvano.votosapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AssociadoJaVotouNessaSessaoException extends RuntimeException {    
    public AssociadoJaVotouNessaSessaoException(){
        super("Associado ja votou nessa sessão!");
    }
}
