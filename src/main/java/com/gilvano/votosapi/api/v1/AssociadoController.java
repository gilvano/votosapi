package com.gilvano.votosapi.api.v1;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gilvano.votosapi.api.v1.request.AssociadoRequest;
import com.gilvano.votosapi.api.v1.response.AssociadoResponse;
import com.gilvano.votosapi.model.Associado;
import com.gilvano.votosapi.service.AssociadoService;

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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/associado")
@AllArgsConstructor
@Tag(name = "Associado", description = "Controller Associado")
public class AssociadoController {

    @Autowired
    private final AssociadoService associadoService;

    @PostMapping(produces = { "application/json" })
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar um novo associado")
    public ResponseEntity<AssociadoResponse> salvar(@Valid @RequestBody AssociadoRequest associadoRequest, UriComponentsBuilder uriBuilder){
        Associado associado = associadoService.salvar(associadoRequest.mapToAssociado());
        URI uri = uriBuilder.path("/api/v1/associado/{id}").buildAndExpand(associado.getId()).toUri();
        return ResponseEntity.created(uri).body(new AssociadoResponse(associado));
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar um associado por Id")
    public ResponseEntity<AssociadoResponse> buscarPorId(@PathVariable Long id){
        Optional<Associado> associado = associadoService.BuscarPorId(id);
        
        if (associado.isPresent()) {
			return ResponseEntity.ok(new AssociadoResponse(associado.get()));
        }        
		return ResponseEntity.notFound().build();  
    }  

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todos os associados cadastrados")
    public List<Associado> buscarTodos(){
        return associadoService.buscarTodos();
    }   
}
