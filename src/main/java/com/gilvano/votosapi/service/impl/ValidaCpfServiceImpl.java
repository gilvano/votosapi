package com.gilvano.votosapi.service.impl;

import com.gilvano.votosapi.api.v1.response.ValidaCpfResponse;
import com.gilvano.votosapi.client.ConsultaCpfClient;
import com.gilvano.votosapi.service.ValidaCpfService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class ValidaCpfServiceImpl implements ValidaCpfService{
    private final ConsultaCpfClient consultaCpf;
    private static final String ABLE_TO_VOTE = "ABLE_TO_VOTE";

    public boolean associadoPodeVotar(String cpf) {
        log.info("Validando no webservice se o associado com o CPF {} está apto para votar", cpf);
        try {            
            ValidaCpfResponse retorno = consultaCpf.buscarPorCPF(cpf);

            if (ABLE_TO_VOTE.equals(retorno.getStatus().toUpperCase())) {
                return Boolean.TRUE;
            }

            return Boolean.FALSE;
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF não é válido");
        }
    }    
}
