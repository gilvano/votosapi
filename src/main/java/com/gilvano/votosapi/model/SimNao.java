package com.gilvano.votosapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SimNao {
    SIM("SIM"),
    NAO("NAO");

    @Getter private String value;
}