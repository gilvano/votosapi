package com.gilvano.votosapi.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.gilvano.votosapi.api.v1.request.SessaoVotacaoRequest;
import com.gilvano.votosapi.api.v1.response.ResultadoSessaoResponse;
import com.gilvano.votosapi.model.Pauta;
import com.gilvano.votosapi.model.SessaoVotacao;
import com.gilvano.votosapi.repository.SessaoVotacaoRepository;
import com.gilvano.votosapi.repository.VotoRepository;
import com.gilvano.votosapi.service.PautaService;
import com.gilvano.votosapi.service.SessaoVotacaoService;
import com.gilvano.votosapi.util.TipoSimNao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SessaoVotacaoServiceImpl implements SessaoVotacaoService {

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    private PautaService pautaService;

    @Autowired
    private VotoRepository votoRepository;

    
    public SessaoVotacao salvar(SessaoVotacaoRequest sessaoVotacaoRequest) {
        Pauta pauta = buscarPauta(sessaoVotacaoRequest);

        ValidarSessaoAtivaParaPauta(pauta);
                      
        SessaoVotacao sessaoVotacao = montarSessaoVotacao(sessaoVotacaoRequest, pauta);

        return sessaoVotacaoRepository.save(sessaoVotacao);
    }
    
    public List<SessaoVotacao> buscarTodos() {
        return sessaoVotacaoRepository.findAll();
    }
    
    public Optional<SessaoVotacao> BuscarPorId(Long id) {
        return sessaoVotacaoRepository.findById(id);
    }

    public ResultadoSessaoResponse buscarResultadoPorId(Long id){
        log.info("Buscando resultado da sessão {}", id);

        SessaoVotacao sessao = sessaoVotacaoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão de Votação não encontrada." ));
        
        if(sessao.Ativa()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessao de Votacao ainda não foi encerrada." );    
        }
        
        return ResultadoSessaoResponse.builder()
                .pauta(sessao.getPauta())
                .dataAbertura(sessao.getDataCriacao())
                .dataFechamento(sessao.getDataFinalizacao())
                .totalVotosSim(votoRepository.countVotoBySessaoVotacao_IdAndVoto(id, TipoSimNao.SIM))
                .totalVotosNao(votoRepository.countVotoBySessaoVotacao_IdAndVoto(id, TipoSimNao.NAO))
                .build();
    }
    
    private Pauta buscarPauta(SessaoVotacaoRequest sessaoVotacaoRequest) {
        log.info("Buscando pauta {} para criar a sessão de votação", sessaoVotacaoRequest.getIdPauta());
        return pautaService.BuscarPorId(sessaoVotacaoRequest.getIdPauta())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pauta não encontrada"));
    }

    private SessaoVotacao montarSessaoVotacao(SessaoVotacaoRequest sessaoVotacaoRequest, Pauta pauta) {
        SessaoVotacao sessao = SessaoVotacao.builder()
                .pauta(pauta)
                .minutosDisponivel(getMinutosDisponivel(sessaoVotacaoRequest))
                .dataCriacao(LocalDateTime.now())
                .dataFinalizacao(LocalDateTime.now().plusMinutes(getMinutosDisponivel(sessaoVotacaoRequest)))
                .build();
        
        log.info("Criando uma sessão de votação para a pauta {} - {}, na data: {}, que ficará disponível por {} minutos",
                sessao.getPauta().getId(),
                sessao.getPauta().getDescricao(), 
                sessao.getDataCriacao(),
                sessao.getMinutosDisponivel()
            ); 
        
        return sessao;
    }

    private Integer getMinutosDisponivel(SessaoVotacaoRequest sessaoVotacaoRequest) {
        return Optional.ofNullable(sessaoVotacaoRequest.getMinutosDisponivel()).orElse(1);
    }    

    private void ValidarSessaoAtivaParaPauta(Pauta pauta) {
        log.info("Validando se existe uma sessão ativa para a pauta {} - {}", pauta.getId(), pauta.getDescricao());

        Optional<SessaoVotacao> sessao = sessaoVotacaoRepository
                                            .buscarPorPautaAnddataFinalizacao(pauta.getId(), LocalDateTime.now());
        if(sessao.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                                              "Ainda existe uma sessao de votação ativa para a Pauta" + pauta.getId());
        }        
    }
}
