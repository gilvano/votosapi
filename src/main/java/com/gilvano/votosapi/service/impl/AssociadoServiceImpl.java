package com.gilvano.votosapi.service.impl;

import java.util.List;
import java.util.Optional;

import com.gilvano.votosapi.model.Associado;
import com.gilvano.votosapi.repository.AssociadoRepository;
import com.gilvano.votosapi.service.AssociadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AssociadoServiceImpl implements AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;
    
    @Override
    public Associado salvar(Associado associado) {
        Optional<Associado> associadoExistente = associadoRepository.findByCpf(associado.getCpf());
        if (associadoExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Associado já cadastrado");
        }

        return associadoRepository.save(associado);
    }

    @Override
    public List<Associado> buscarTodos() {
        return associadoRepository.findAll();
    }

    @Override
    public Optional<Associado> BuscarPorId(Long id) {
        return associadoRepository.findById(id);
    }

    @Override
    public Optional<Associado> BuscarPorCpf(String cpf) {
        return associadoRepository.findByCpf(cpf);    
    };
    
}
