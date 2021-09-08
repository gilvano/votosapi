package com.gilvano.votosapi.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.gilvano.votosapi.api.v1.response.ValidaCpfResponse;
import com.gilvano.votosapi.service.ValidaCpfService;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.webjars.NotFoundException;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ValidaCpfServiceImpl implements ValidaCpfService{

    private static final String url = "https://user-info.herokuapp.com/users/{cpf}";
    private static final String ABLE_TO_VOTE = "ABLE_TO_VOTE";

    public boolean associadoPodeVotar(String cpf) {
        log.info("Validando no webservice se o associado com o CPF {} está apto para votar", cpf);
        try {            
            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> params = new HashMap<String, String>();
            params.put("cpf", cpf);
            ValidaCpfResponse retorno = restTemplate.getForObject(url, ValidaCpfResponse.class, params);

            if (ABLE_TO_VOTE.equals(retorno.getStatus().toUpperCase())) {
                return Boolean.TRUE;
            }

            return Boolean.FALSE;
        }
        catch (HttpClientErrorException e) {
            throw new NotFoundException("CPF não é válido");
        }
    }

    
}
