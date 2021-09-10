package com.gilvano.votosapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AssociadoNaoEncontradoException extends RuntimeException {    
    public AssociadoNaoEncontradoException(){
        super("Associado não encontrado!");
    }
}
