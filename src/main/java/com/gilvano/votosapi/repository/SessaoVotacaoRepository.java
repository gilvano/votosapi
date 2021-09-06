package com.gilvano.votosapi.repository;

import com.gilvano.votosapi.model.SessaoVotacao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {
    
}
