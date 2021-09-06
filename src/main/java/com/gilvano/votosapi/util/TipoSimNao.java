package com.gilvano.votosapi.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TipoSimNao {
    SIM("SIM"),
    NAO("NAO");

    @Getter private String value;
}