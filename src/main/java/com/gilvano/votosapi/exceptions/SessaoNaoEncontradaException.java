package com.gilvano.votosapi.exceptions;

public class SessaoNaoEncontradaException extends RuntimeException {    
    public SessaoNaoEncontradaException(){
        super("Sessão não encontrada");
    }
}
