package com.gilvano.votosapi.controller;

import java.util.List;

import com.gilvano.votosapi.model.Associado;
import com.gilvano.votosapi.service.AssociadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/associado")
@AllArgsConstructor
@Tag(name = "Pauta", description = "Controller Pauta")
public class AssociadoController {

    @Autowired
    private AssociadoService associadoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar um novo associado")
    public Associado salvar(@RequestBody Associado associado){
        return associadoService.salvar(associado);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar um associado por Id")
    public Associado buscarPorId(@PathVariable Long id){
        return associadoService.BuscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Associado nao encontrado." ));
    }  

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todos os associados cadastrados")
    public List<Associado> buscarTodos(){
        return associadoService.buscarTodos();
    }   
}
