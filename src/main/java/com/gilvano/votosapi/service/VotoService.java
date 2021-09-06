package com.gilvano.votosapi.service;

import java.util.List;

import com.gilvano.votosapi.model.Voto;

public interface VotoService {
    public Voto salvar(Voto voto);   
    public List<Voto> buscarTodos(); 
}
