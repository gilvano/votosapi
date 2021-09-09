package com.gilvano.votosapi.service;

import com.gilvano.votosapi.api.v1.response.ResultadoSessaoResponse;

public interface EnviaResultadoService {
    public void enviarResultado(ResultadoSessaoResponse resultado);
}
