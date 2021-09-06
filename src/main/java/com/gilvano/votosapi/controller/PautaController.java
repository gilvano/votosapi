package com.gilvano.votosapi.controller;

import java.util.List;

import com.gilvano.votosapi.model.Pauta;
import com.gilvano.votosapi.service.PautaService;

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
@RequestMapping("/api/pauta")
@AllArgsConstructor
@Tag(name = "Pauta", description = "Controller Pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar uma nova pauta")
    public Pauta salvar(@RequestBody Pauta pauta){
        return pautaService.salvar(pauta);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar uma pauta por Id")
    public Pauta buscarPorId(@PathVariable Long id){
        return pautaService.BuscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pauta nao encontrada." ));
    }  

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todas as pautas")
    public List<Pauta> buscarTodos(){
        return pautaService.buscarTodos();
    }
    
}
