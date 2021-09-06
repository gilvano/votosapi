package com.gilvano.votosapi.api.v1.response;

import com.gilvano.votosapi.model.Associado;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssociadoResponse {

    private Long id;
    private String nome;
    private String cpf;
    
    public AssociadoResponse(Associado associado) {
        this.id = associado.getId();
        this.cpf =  associado.getCpf();
        this.nome =  associado.getNome();
	}
   
}
