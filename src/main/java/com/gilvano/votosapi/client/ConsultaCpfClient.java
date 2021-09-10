package com.gilvano.votosapi.client;

import com.gilvano.votosapi.api.v1.response.ValidaCpfResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://user-info.herokuapp.com/", name = "ConsultaCPF")
public interface ConsultaCpfClient {
    @GetMapping("users/{cpf}")
    ValidaCpfResponse buscarPorCPF(@PathVariable("cpf") String cpf);    
}
