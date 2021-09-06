package com.gilvano.votosapi.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessaoVotacao {

    @Id    
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne()
    @NotNull
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;

    @NotNull
    private LocalDateTime dataCriacao = LocalDateTime.now();

    private Integer minutosDisponivel = 1;
    
}
