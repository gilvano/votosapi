package com.gilvano.votosapi.api.v1;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gilvano.votosapi.api.v1.request.PautaRequest;
import com.gilvano.votosapi.model.Pauta;
import com.gilvano.votosapi.service.PautaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/pauta")
@AllArgsConstructor
@Tag(name = "Pauta", description = "Controller Pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService;

    @PostMapping(produces = { "application/json" })
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar uma nova pauta")
    public ResponseEntity<Pauta> salvar(@Valid @RequestBody PautaRequest pautaRequest, UriComponentsBuilder uriBuilder){
        Pauta pauta = pautaService.salvar(pautaRequest.mapToPauta());
        URI uri = uriBuilder
                    .path("/api/v1/pauta/{id}")
                    .buildAndExpand(pauta.getId()).toUri();

        return ResponseEntity.created(uri).body(pauta);
    }

    @GetMapping("{id}") 
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar uma pauta por Id")
    public ResponseEntity<Pauta> buscarPorId(@PathVariable Long id){
        Optional<Pauta> pauta = pautaService.BuscarPorId(id);

        if (pauta.isPresent()) {
            return ResponseEntity.ok(pauta.get());
        }        
        return ResponseEntity.notFound().build();                 
    }  

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todas as pautas")
    public List<Pauta> buscarTodos(){
        return pautaService.buscarTodos();
    }
    
}
