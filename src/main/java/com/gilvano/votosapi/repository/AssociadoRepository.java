package com.gilvano.votosapi.repository;

import com.gilvano.votosapi.model.Associado;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {
    
}
