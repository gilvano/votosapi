package com.gilvano.votosapi.service;

import java.util.List;
import java.util.Optional;

import com.gilvano.votosapi.api.v1.request.SessaoVotacaoRequest;
import com.gilvano.votosapi.api.v1.response.ResultadoSessaoResponse;
import com.gilvano.votosapi.model.SessaoVotacao;

public interface SessaoVotacaoService {
    SessaoVotacao salvar(SessaoVotacaoRequest sessaoVotacaoRequest);  
    List<SessaoVotacao> buscarTodos();
    Optional<SessaoVotacao> BuscarPorId(Long id);   
    ResultadoSessaoResponse buscarResultadoPorId(Long id);
    void atualizarDataEnvioIntegracao(Long idSessao);
}
