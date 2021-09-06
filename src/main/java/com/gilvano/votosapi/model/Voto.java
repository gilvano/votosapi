package com.gilvano.votosapi.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.gilvano.votosapi.util.TipoSimNao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voto {

    @Id    
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @NotNull   
    @JoinColumn(name = "id_associado") 
    private Associado associado;

    @Enumerated(EnumType.STRING)    
    @NotNull
	private TipoSimNao voto;    

    private LocalDateTime dataVoto = LocalDateTime.now();

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "id_sessao_votacao")
    private SessaoVotacao sessaoVotacao;
    
}
