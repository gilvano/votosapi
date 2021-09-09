package com.gilvano.votosapi.component;

import java.time.LocalDateTime;
import java.util.List;

import com.gilvano.votosapi.api.v1.response.ResultadoSessaoResponse;
import com.gilvano.votosapi.model.SessaoVotacao;
import com.gilvano.votosapi.repository.SessaoVotacaoRepository;
import com.gilvano.votosapi.service.EnviaResultadoService;
import com.gilvano.votosapi.service.SessaoVotacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class EnviaResultadoTask {

    @Autowired
    private SessaoVotacaoService service;

    @Autowired
    private SessaoVotacaoRepository repository;

    @Autowired
    private EnviaResultadoService enviaResultado;
    
    @Scheduled(fixedRate = 60000)
	public void reportCurrentTime() {
		log.info("Executando integração de resultados das votações");

        try{
            List<SessaoVotacao> sessoes = repository.buscarSessoesFinalizadasENaoEnviadas(LocalDateTime.now());
            for (SessaoVotacao sessaoVotacao : sessoes) {
                log.info("Buscando resultado da sessão {}", sessaoVotacao.getId());
                ResultadoSessaoResponse resultado = service.buscarResultadoPorId(sessaoVotacao.getId());  

                log.info("Resultado da sessão {} com a pauta {} - {}, votos sim {} e votos não {}",
                            sessaoVotacao.getId(),
                            resultado.getPauta().getId(),
                            resultado.getPauta().getDescricao(),
                            resultado.getTotalVotosSim(),
                            resultado.getTotalVotosNao()
                        ); 
                             
                enviaResultado.enviarResultado(resultado);  
                
                log.info("Resultado da sessão {} foi enviado", sessaoVotacao.getId());
                service.atualizarDataEnvioIntegracao(sessaoVotacao.getId());
            }
            
        } catch (Exception e){
            log.warn("Erro ao enviar resultado: {}", e.getMessage());
        }
	}
}
