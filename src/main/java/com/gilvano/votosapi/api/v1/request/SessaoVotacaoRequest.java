package com.gilvano.votosapi.api.v1.request;

import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessaoVotacaoRequest {

    private Long idPauta;

    @Positive(message = "Deve ser um numero positivo")
    private Integer minutosDisponivel;     
}
