package com.gilvano.votosapi.api.v1;

import java.util.List;

import javax.validation.Valid;

import com.gilvano.votosapi.api.v1.request.SessaoVotacaoRequest;
import com.gilvano.votosapi.api.v1.response.ResultadoSessaoResponse;
import com.gilvano.votosapi.model.SessaoVotacao;
import com.gilvano.votosapi.service.SessaoVotacaoService;

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
@RequestMapping("/api/v1/sessaovotacao")
@AllArgsConstructor
@Tag(name = "Sessao de Votacao", description = "Controller SessaoVotacao")
public class SessaoVotacaoController {

    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;

    @PostMapping(produces = { "application/json" })
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar uma nova sessao de votacao")
    public SessaoVotacao save(@Valid @RequestBody SessaoVotacaoRequest sessaoVotacao){
        return sessaoVotacaoService.salvar(sessaoVotacao);
    }

    @GetMapping(value = "{id}", produces = { "application/json" })
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar uma sessao por Id")
    public SessaoVotacao buscarPorId(@PathVariable Long id){
        return sessaoVotacaoService.BuscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessao de Votacao nao encontrada." ));
    }  

    @GetMapping(produces = { "application/json" })
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todas as sessoes")
    public List<SessaoVotacao> buscarTodos(){
        return sessaoVotacaoService.buscarTodos();
    }

    @GetMapping(value = "resultado/{id}", produces = { "application/json" })
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar resultado da sessão por id")
    public ResultadoSessaoResponse buscarResultadoPorId(@PathVariable Long id){
        return sessaoVotacaoService.buscarResultadoPorId(id);
    }
    
}
