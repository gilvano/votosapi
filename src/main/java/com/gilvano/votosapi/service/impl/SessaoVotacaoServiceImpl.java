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

@Service
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
        SessaoVotacao sessao = sessaoVotacaoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessao de Votacao nao encontrada." ));
        
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
        return pautaService.BuscarPorId(sessaoVotacaoRequest.getIdPauta())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pauta nao encontrada"));
    }

    private SessaoVotacao montarSessaoVotacao(SessaoVotacaoRequest sessaoVotacaoRequest, Pauta pauta) {
        return SessaoVotacao.builder()
                .pauta(pauta)
                .minutosDisponivel(getMinutosDisponivel(sessaoVotacaoRequest))
                .dataCriacao(LocalDateTime.now())
                .dataFinalizacao(LocalDateTime.now().plusMinutes(getMinutosDisponivel(sessaoVotacaoRequest)))
                .build();
    }

    private Integer getMinutosDisponivel(SessaoVotacaoRequest sessaoVotacaoRequest) {
        return Optional.ofNullable(sessaoVotacaoRequest.getMinutosDisponivel()).orElse(1);
    }    

    private void ValidarSessaoAtivaParaPauta(Pauta pauta) {
        Optional<SessaoVotacao> sessao = sessaoVotacaoRepository
                                            .buscarPorPautaAnddataFinalizacao(pauta.getId(), LocalDateTime.now());
        if(sessao.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                                              "Ainda existe uma sessao de votação ativa para a Pauta" + pauta.getId());
        }        
    }
}
