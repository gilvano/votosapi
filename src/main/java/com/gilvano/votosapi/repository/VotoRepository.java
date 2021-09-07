package com.gilvano.votosapi.repository;

import java.util.Optional;

import com.gilvano.votosapi.model.Voto;
import com.gilvano.votosapi.util.TipoSimNao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    Optional<Voto> findByAssociado_IdAndSessaoVotacao_id(Long idAssociado, Long idSessaoVotacao);
    Integer countVotoBySessaoVotacao_IdAndVoto(Long idSessaoVotacao, TipoSimNao voto);
}
