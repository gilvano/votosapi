package com.gilvano.votosapi.api.v1;

import com.gilvano.votosapi.api.v1.request.VotoRequest;
import com.gilvano.votosapi.model.Voto;
import com.gilvano.votosapi.service.VotoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/voto")
@AllArgsConstructor
@Tag(name = "Voto", description = "Controller Voto")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @PostMapping(produces = { "application/json" })
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar um novo voto")
    public Voto save(@RequestBody VotoRequest votoRequest){
        return votoService.salvar(votoRequest);
    }
    
}
