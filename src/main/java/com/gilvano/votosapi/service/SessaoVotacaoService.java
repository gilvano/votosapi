package com.gilvano.votosapi.service;

import java.util.List;
import java.util.Optional;

import com.gilvano.votosapi.model.SessaoVotacao;

public interface SessaoVotacaoService {
    public SessaoVotacao salvar(SessaoVotacao sessaoVotacao);  
    public List<SessaoVotacao> buscarTodos();
    public Optional<SessaoVotacao> BuscarPorId(Long id);   
}
