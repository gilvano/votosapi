package com.gilvano.votosapi.service.impl;

import java.util.List;

import com.gilvano.votosapi.model.Voto;
import com.gilvano.votosapi.repository.VotoRepository;
import com.gilvano.votosapi.service.VotoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotoServiceImpl implements VotoService {

    @Autowired
    private VotoRepository votoRepository;
    
    public Voto salvar(Voto voto) {
        return votoRepository.save(voto);
    }

    public List<Voto> buscarTodos() {
        return votoRepository.findAll();
    }
    
}
