package com.gilvano.votosapi.service.impl;

import java.util.List;
import java.util.Optional;

import com.gilvano.votosapi.model.Pauta;
import com.gilvano.votosapi.repository.PautaRepository;
import com.gilvano.votosapi.service.PautaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaServiceImpl implements PautaService {

    @Autowired
    private PautaRepository pautaRepository;
    
    public Pauta salvar(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    @Override
    public List<Pauta> buscarTodos() {
        return pautaRepository.findAll();
    }

    @Override
    public Optional<Pauta> BuscarPorId(Long id) {
        return pautaRepository.findById(id);
    }
    
}
