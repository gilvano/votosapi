package com.gilvano.votosapi.service.impl;

import java.util.List;
import java.util.Optional;

import com.gilvano.votosapi.model.SessaoVotacao;
import com.gilvano.votosapi.repository.SessaoVotacaoRepository;
import com.gilvano.votosapi.service.SessaoVotacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessaoVotacaoServiceImpl implements SessaoVotacaoService {

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;
    
    public SessaoVotacao salvar(SessaoVotacao sessaoVotacao) {
        return sessaoVotacaoRepository.save(sessaoVotacao);
    }

    public List<SessaoVotacao> buscarTodos() {
        return sessaoVotacaoRepository.findAll();
    }

    public Optional<SessaoVotacao> BuscarPorId(Long id) {
        return sessaoVotacaoRepository.findById(id);
    }
    
}
