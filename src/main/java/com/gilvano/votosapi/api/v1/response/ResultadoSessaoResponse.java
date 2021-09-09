package com.gilvano.votosapi.api.v1.response;

import com.gilvano.votosapi.model.Pauta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ResultadoSessaoResponse {
    private Long IdSessao;
    private Pauta pauta;    
    private Integer totalVotosSim;
    private Integer totalVotosNao;
    private String dataAbertura;
    private String dataFechamento;
}
