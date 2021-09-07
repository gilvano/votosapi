package com.gilvano.votosapi.api.v1.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.gilvano.votosapi.model.Associado;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoRequest {

    @NotNull 
    @NotEmpty 
    private String nome; 

    @NotNull 
    @NotEmpty
    @Pattern(regexp = "[\\s]*[0-9]*[1-9]+", message="Informe apenas os números do CPF sem . e -")
    @Length(min = 11)
    private String cpf;

    public Associado mapToAssociado() {		
        return new Associado(cpf, nome);
	}    
}
