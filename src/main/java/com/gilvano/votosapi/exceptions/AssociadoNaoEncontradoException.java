package com.gilvano.votosapi.exceptions;

public class AssociadoNaoEncontradoException extends RuntimeException {    
    public AssociadoNaoEncontradoException(){
        super("Associado não encontrado!");
    }
}
