package com.gilvano.votosapi.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.gilvano.votosapi.model.SessaoVotacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {
    List<SessaoVotacao> findByPauta_id(Long pauta);  

    @Query("select s from SessaoVotacao s where s.dataEnvioIntegracao is null and s.dataFinalizacao < :dataAtual")
    List<SessaoVotacao> buscarSessoesFinalizadasENaoEnviadas(LocalDateTime dataAtual);
    
    @Query("select s from SessaoVotacao s where s.pauta.id =:idPauta and s.dataFinalizacao >= :dataAtual")
    Optional<SessaoVotacao> buscarPorPautaAnddataFinalizacao(Long idPauta, LocalDateTime dataAtual); 

    @Modifying
    @Query("update SessaoVotacao s set s.dataEnvioIntegracao = :dataAtual where s.id = :idSessao")
    int setdataEnvioIntegracaoForSessaoVotacao(LocalDateTime dataAtual, Long idSessao);
}
