package com.gilvano.votosapi.service;

import java.util.List;
import java.util.Optional;

import com.gilvano.votosapi.model.Pauta;

public interface PautaService {
    Pauta salvar(Pauta pauta);   
    List<Pauta> buscarTodos();
    Optional<Pauta> BuscarPorId(Long id); 
}
