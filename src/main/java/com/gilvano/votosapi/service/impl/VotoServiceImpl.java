package com.gilvano.votosapi.service.impl;

import java.util.List;
import java.util.Optional;

import com.gilvano.votosapi.api.v1.exceptions.AssociadoJaVotouNessaSessao;
import com.gilvano.votosapi.api.v1.exceptions.AssociadoNaoPodeVotarException;
import com.gilvano.votosapi.api.v1.request.VotoRequest;
import com.gilvano.votosapi.api.v1.response.VotoResponse;
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

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class VotoServiceImpl implements VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private AssociadoService associadoService;    

    @Autowired
    private SessaoVotacaoService sessaoVotacaoService; 

    @Autowired
    private ValidaCpfService validaCpfService;
    
    public VotoResponse salvar(VotoRequest votoRequest) {
        try {
            Voto voto = criarVoto(votoRequest); 
            validarUsuarioJaVotou(voto);
            validarSessaoEstaAtiva(votoRequest);
            validarUsuarioPodeVotar(votoRequest);                    
            
            log.info("Cadastrando voto do associado com o CPF {} na pauta {} - {}", 
                        voto.getAssociado().getCpf(),
                        voto.getSessaoVotacao().getPauta().getId(),
                        voto.getSessaoVotacao().getPauta().getDescricao());

            votoRepository.save(voto);

            return new VotoResponse("Voto computado com sussesso");
        } catch (Exception e) {
            return new VotoResponse("Não foi possível processar o voto. Motivo: " + e.getMessage());   
        }
    }            

    public List<Voto> buscarTodos() {
        return votoRepository.findAll();
    }

    private void validarUsuarioPodeVotar(VotoRequest votoRequest) {
        log.info("Validando se o associado com o CPF {} pode votar", votoRequest.getCpf());
        if (!validaCpfService.associadoPodeVotar(votoRequest.getCpf())){
            log.warn("O associado com o CPF {} não está apto para votar", votoRequest.getCpf());
            throw new AssociadoNaoPodeVotarException();
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
        log.info("Validando se a sessão de votação {} está ativa", votoRequest.getSessaoVotacao());
        SessaoVotacao sessao = sessaoVotacaoService.BuscarPorId(votoRequest.getSessaoVotacao())
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sessao nao encontrada"));
        if(!sessao.Ativa()){
            log.warn("A sessão de votação {} não está ativa", votoRequest.getSessaoVotacao());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sessão não está ativa");
        }
    }

    private void validarUsuarioJaVotou(Voto voto) {
        log.info("Validando se o associado com o CPF {} já votou na sessão {}", 
                    voto.getAssociado().getCpf(),
                    voto.getSessaoVotacao().getId());

        Optional<Voto> votoExistente = votoRepository
                                        .findByAssociado_IdAndSessaoVotacao_id(
                                            voto.getAssociado().getId(), 
                                            voto.getSessaoVotacao().getId());   
        if (votoExistente.isPresent())                                            {
            log.info("O associado com o CPF {} já votou na sessão {}", 
                        voto.getAssociado().getCpf(),
                        voto.getSessaoVotacao().getId());
                        
            throw new AssociadoJaVotouNessaSessao();
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
