package com.gilvano.votosapi.api.v1.response;

import java.time.LocalDateTime;

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
    private Pauta pauta;    
    private Integer totalVotosSim;
    private Integer totalVotosNao;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
}
