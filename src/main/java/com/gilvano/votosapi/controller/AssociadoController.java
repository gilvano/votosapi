package com.gilvano.votosapi.controller;

import java.util.List;

import com.gilvano.votosapi.model.Associado;
import com.gilvano.votosapi.repository.AssociadoRepository;
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

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/associado")
@AllArgsConstructor
public class AssociadoController {

    @Autowired
    private AssociadoService associadoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Associado salvar(@RequestBody Associado associado){
        return associadoService.salvar(associado);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Associado buscarPorId(@PathVariable Long id){
        return associadoService.BuscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Associado nao encontrado." ));
    }  

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Associado> buscarTodos(){
        return associadoService.buscarTodos();
    }   
}
