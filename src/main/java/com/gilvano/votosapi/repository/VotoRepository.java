package com.gilvano.votosapi.repository;

import com.gilvano.votosapi.model.Voto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    
}
