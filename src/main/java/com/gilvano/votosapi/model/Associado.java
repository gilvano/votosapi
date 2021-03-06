package com.gilvano.votosapi.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Associado {

    @Id    
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "nome", nullable = false)
    private String nome; 

    @Column(unique=true)
    private String cpf;

    @NotNull
    private LocalDateTime dataCadastro = LocalDateTime.now();

    public Associado(String cpf, String nome){
        this.cpf = cpf;
        this.nome = nome;
    }    
}
