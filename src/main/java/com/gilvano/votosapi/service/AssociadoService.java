package com.gilvano.votosapi.service;

import java.util.List;
import java.util.Optional;

import com.gilvano.votosapi.model.Associado;

public interface AssociadoService {
    public Associado salvar(Associado associado);    
    public List<Associado> buscarTodos();
    public Optional<Associado> BuscarPorId(Long id);
}

