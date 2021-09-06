package com.gilvano.votosapi.service;

import java.util.List;
import java.util.Optional;

import com.gilvano.votosapi.model.Pauta;

public interface PautaService {
    public Pauta salvar(Pauta pauta);   
    public List<Pauta> buscarTodos();
    public Optional<Pauta> BuscarPorId(Long id); 
}
