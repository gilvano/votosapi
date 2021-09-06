package com.gilvano.votosapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pauta {

    @Id    
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "descricao", nullable = false)
    private String descricao; 
    
}
