package com.gilvano.votosapi.service.impl;

import java.util.List;
import java.util.Optional;

import com.gilvano.votosapi.api.v1.request.VotoRequest;
import com.gilvano.votosapi.model.Associado;
import com.gilvano.votosapi.model.SessaoVotacao;
import com.gilvano.votosapi.model.Voto;
import com.gilvano.votosapi.repository.VotoRepository;
import com.gilvano.votosapi.service.AssociadoService;
import com.gilvano.votosapi.service.SessaoVotacaoService;
import com.gilvano.votosapi.service.ValidaCpfService;
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

    @Autowired
    private ValidaCpfService validaCpfService;
    
    public Voto salvar(VotoRequest votoRequest) {
        validarSessaoEstaAtiva(votoRequest);
        validarUsuarioPodeVotar(votoRequest);

        Voto voto = criarVoto(votoRequest);
        
        
        validarUsuarioJaVotou(voto);

        return votoRepository.save(voto);
    }            

    public List<Voto> buscarTodos() {
        return votoRepository.findAll();
    }

    private void validarUsuarioPodeVotar(VotoRequest votoRequest) {
        if (!validaCpfService.associadoPodeVotar(votoRequest.getCpf())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Associado nao esta apto para votar");
        }
    }

    private Voto criarVoto(VotoRequest votoRequest) {
        return Voto.builder()
                        .associado(buscarAssociado(votoRequest))
                        .sessaoVotacao(buscarSessaoVotacao(votoRequest))
                        .voto(votoRequest.getVoto())
                        .build();
    }

    private void validarSessaoEstaAtiva(VotoRequest votoRequest) {
        SessaoVotacao sessao = sessaoVotacaoService.BuscarPorId(votoRequest.getSessaoVotacao())
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sessao nao encontrada"));
        if(!sessao.Ativa()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sessão não está ativa");
        }
    }

    private void validarUsuarioJaVotou(Voto voto) {
        Optional<Voto> votoExistente = votoRepository
                                        .findByAssociado_IdAndSessaoVotacao_id(
                                            voto.getAssociado().getId(), 
                                            voto.getSessaoVotacao().getId());   
        if (votoExistente.isPresent())                                            {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Associado ja votou nessa sessao");
        }
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
