package com.gilvano.votosapi.service;

import java.util.List;
import java.util.Optional;

import com.gilvano.votosapi.api.v1.request.SessaoVotacaoRequest;
import com.gilvano.votosapi.model.SessaoVotacao;

public interface SessaoVotacaoService {
    public SessaoVotacao salvar(SessaoVotacaoRequest sessaoVotacaoRequest);  
    public List<SessaoVotacao> buscarTodos();
    public Optional<SessaoVotacao> BuscarPorId(Long id);   
}
