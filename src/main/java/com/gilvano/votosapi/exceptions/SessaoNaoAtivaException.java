package com.gilvano.votosapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SessaoNaoAtivaException extends RuntimeException {    
    public SessaoNaoAtivaException(){
        super("Sessão não está ativa!");
    }
}
