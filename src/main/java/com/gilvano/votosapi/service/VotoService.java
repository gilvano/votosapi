package com.gilvano.votosapi.service;

import java.util.List;

import com.gilvano.votosapi.api.v1.request.VotoRequest;
import com.gilvano.votosapi.api.v1.response.VotoResponse;
import com.gilvano.votosapi.model.Voto;

public interface VotoService {
    VotoResponse salvar(VotoRequest votoRequest);   
    List<Voto> buscarTodos(); 
}
