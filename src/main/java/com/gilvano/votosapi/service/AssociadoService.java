package com.gilvano.votosapi.service;

import java.util.List;
import java.util.Optional;

import com.gilvano.votosapi.model.Associado;

public interface AssociadoService {
    Associado salvar(Associado associado);    
    List<Associado> buscarTodos();
    Optional<Associado> BuscarPorId(Long id);
    Optional<Associado> BuscarPorCpf(String cpf);
}

