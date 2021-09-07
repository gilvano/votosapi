package com.gilvano.votosapi.api.v1.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.gilvano.votosapi.util.TipoSimNao;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VotoRequest {

    @NotNull 
    @NotEmpty 
    @Length(min = 11)
    @Pattern(regexp = "[\\s]*[0-9]*[1-9]+", message="Informe apenas os números do CPF sem . e -")
    private String cpf;
       
    @NotNull 
    @NotEmpty 
	private TipoSimNao voto;    

    @NotNull
    private Long sessaoVotacao;
    
}
