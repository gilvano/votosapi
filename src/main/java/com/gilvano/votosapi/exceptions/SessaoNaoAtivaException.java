package com.gilvano.votosapi.exceptions;

public class SessaoNaoAtivaException extends RuntimeException {    
    public SessaoNaoAtivaException(){
        super("Sessão não está ativa!");
    }
}
