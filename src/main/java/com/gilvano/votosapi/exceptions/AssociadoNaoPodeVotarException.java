package com.gilvano.votosapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AssociadoNaoPodeVotarException extends RuntimeException {    
    public AssociadoNaoPodeVotarException(){
        super("Associado nao esta apto para votar!");
    }
}
