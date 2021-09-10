package com.gilvano.votosapi.exceptions;

public class AssociadoNaoPodeVotarException extends RuntimeException {    
    public AssociadoNaoPodeVotarException(){
        super("Associado nao esta apto para votar!");
    }
}
