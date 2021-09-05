package com.gilvano.votosapi.service.impl;

import java.util.List;
import java.util.Optional;

import com.gilvano.votosapi.model.Associado;
import com.gilvano.votosapi.repository.AssociadoRepository;
import com.gilvano.votosapi.service.AssociadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociadoServiceImpl implements AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;
    
    @Override
    public Associado salvar(Associado associado) {
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
    
}
