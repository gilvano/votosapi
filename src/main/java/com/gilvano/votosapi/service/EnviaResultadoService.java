package com.gilvano.votosapi.service;

import com.gilvano.votosapi.api.v1.response.ResultadoSessaoResponse;

public interface EnviaResultadoService {
    void enviarResultado(ResultadoSessaoResponse resultado);
}
