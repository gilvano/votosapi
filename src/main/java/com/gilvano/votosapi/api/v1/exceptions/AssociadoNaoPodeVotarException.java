package com.gilvano.votosapi.api.v1.exceptions;

public class AssociadoNaoPodeVotarException extends RuntimeException {    
    public AssociadoNaoPodeVotarException(){
        super("Associado nao esta apto para votar!");
    }
}
