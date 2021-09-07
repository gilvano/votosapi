package com.gilvano.votosapi.service.impl;

import java.util.List;

import com.gilvano.votosapi.api.v1.request.VotoRequest;
import com.gilvano.votosapi.model.Associado;
import com.gilvano.votosapi.model.SessaoVotacao;
import com.gilvano.votosapi.model.Voto;
import com.gilvano.votosapi.repository.VotoRepository;
import com.gilvano.votosapi.service.AssociadoService;
import com.gilvano.votosapi.service.SessaoVotacaoService;
import com.gilvano.votosapi.service.VotoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VotoServiceImpl implements VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private AssociadoService associadoService; 

    @Autowired
    private SessaoVotacaoService sessaoVotacaoService; 
    
    public Voto salvar(VotoRequest votoRequest) {
        Voto voto = Voto.builder()
                        .associado(buscarAssociado(votoRequest))
                        .sessaoVotacao(buscarSessaoVotacao(votoRequest))
                        .voto(votoRequest.getVoto())
                        .build();

        return votoRepository.save(voto);
    }

    public List<Voto> buscarTodos() {
        return votoRepository.findAll();
    }

    private Associado buscarAssociado(VotoRequest votoRequest) {
        return associadoService.BuscarPorCpf(votoRequest.getCpf())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Associado nao encontrado"));
    }

    private SessaoVotacao buscarSessaoVotacao(VotoRequest votoRequest) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.BuscarPorId(votoRequest.getSessaoVotacao())
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sessao nao encontrada"));

        return sessaoVotacao;
    }
    
}
