package com.gilvano.votosapi.repository;

import com.gilvano.votosapi.model.Pauta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
    
}
