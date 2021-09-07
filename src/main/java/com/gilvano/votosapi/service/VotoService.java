package com.gilvano.votosapi.service;

import java.util.List;

import com.gilvano.votosapi.api.v1.request.VotoRequest;
import com.gilvano.votosapi.model.Voto;

public interface VotoService {
    public Voto salvar(VotoRequest votoRequest);   
    public List<Voto> buscarTodos(); 
}
