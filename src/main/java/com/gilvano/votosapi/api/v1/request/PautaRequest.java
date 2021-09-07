package com.gilvano.votosapi.api.v1.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.gilvano.votosapi.model.Pauta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PautaRequest {

    @NotNull 
    @NotEmpty 
    private String descricao; 

    public Pauta mapToPauta() {		
        return new Pauta(descricao);
	}    
}
