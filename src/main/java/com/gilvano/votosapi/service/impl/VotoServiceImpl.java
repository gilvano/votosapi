package com.gilvano.votosapi.service.impl;

import java.util.List;
import java.util.Optional;

import com.gilvano.votosapi.api.v1.request.VotoRequest;
import com.gilvano.votosapi.api.v1.response.VotoResponse;
import com.gilvano.votosapi.exceptions.AssociadoJaVotouNessaSessaoException;
import com.gilvano.votosapi.exceptions.AssociadoNaoEncontradoException;
import com.gilvano.votosapi.exceptions.AssociadoNaoPodeVotarException;
import com.gilvano.votosapi.exceptions.SessaoNaoAtivaException;
import com.gilvano.votosapi.exceptions.SessaoNaoEncontradaException;
import com.gilvano.votosapi.model.Associado;
import com.gilvano.votosapi.model.SessaoVotacao;
import com.gilvano.votosapi.model.Voto;
import com.gilvano.votosapi.repository.VotoRepository;
import com.gilvano.votosapi.service.AssociadoService;
import com.gilvano.votosapi.service.SessaoVotacaoService;
import com.gilvano.votosapi.service.ValidaCpfService;
import com.gilvano.votosapi.service.VotoService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;
    private final AssociadoService associadoService;    
    private final  SessaoVotacaoService sessaoVotacaoService; 
    private final ValidaCpfService validaCpfService;
    
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
                                        .orElseThrow(() -> new SessaoNaoEncontradaException());
        if(!sessao.Ativa()){
            log.warn("A sessão de votação {} não está ativa", votoRequest.getSessaoVotacao());
            throw new SessaoNaoAtivaException();
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
                        
            throw new AssociadoJaVotouNessaSessaoException();
        }
    }

    private Associado buscarAssociado(VotoRequest votoRequest) {
        return associadoService.BuscarPorCpf(votoRequest.getCpf())
                .orElseThrow(() -> new AssociadoNaoEncontradoException());
    }

    private SessaoVotacao buscarSessaoVotacao(VotoRequest votoRequest) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.BuscarPorId(votoRequest.getSessaoVotacao())
                                        .orElseThrow(() -> new SessaoNaoEncontradaException());

        return sessaoVotacao;
    }
    
}
