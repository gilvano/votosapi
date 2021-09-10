package com.gilvano.votosapi.exceptions;

public class AssociadoJaVotouNessaSessaoException extends RuntimeException {    
    public AssociadoJaVotouNessaSessaoException(){
        super("Associado ja votou nessa sessão!");
    }
}
