package com.gilvano.votosapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gilvano.votosapi.api.v1.response.ResultadoSessaoResponse;
import com.gilvano.votosapi.config.RabbitConfig;
import com.gilvano.votosapi.service.EnviaResultadoService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class EnviaResultadoServiceImpl implements EnviaResultadoService {

    private final RabbitTemplate rabbitTemplate;

    public void enviarResultado(ResultadoSessaoResponse resultado){
        try {
            String json = new ObjectMapper().writeValueAsString(resultado);

            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, 
                                          RabbitConfig.ROUTING_KEY, 
                                          json);
        } catch (Exception e) {
            log.warn("Erro ao integrar resultado {}", e.getMessage());
            throw new NotFoundException("Erro ao integrar resultado " + e.getMessage());
        }
    }    
}
