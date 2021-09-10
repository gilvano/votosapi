package com.gilvano.votosapi.api.v1.exceptions;

public class AssociadoJaVotouNessaSessao extends RuntimeException {    
    public AssociadoJaVotouNessaSessao(){
        super("Associado ja votou nessa sessão!");
    }
}
