package com.gilvano.votosapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SessaoNaoEncontradaException extends RuntimeException {    
    public SessaoNaoEncontradaException(){
        super("Sessão não encontrada");
    }
}
