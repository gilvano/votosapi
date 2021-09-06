package com.gilvano.votosapi.api.v1.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.gilvano.votosapi.model.Associado;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssociadoRequest {

        @NotNull @NotEmpty @Length(min = 11)
        private String nome; 

        @NotNull @NotEmpty
        private String cpf;

        public Associado mapToAssociado() {		
		return new Associado(cpf, nome);
	}    
}
