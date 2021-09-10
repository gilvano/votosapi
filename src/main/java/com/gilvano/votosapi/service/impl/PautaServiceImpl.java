package com.gilvano.votosapi.service.impl;

import java.util.List;
import java.util.Optional;

import com.gilvano.votosapi.model.Pauta;
import com.gilvano.votosapi.repository.PautaRepository;
import com.gilvano.votosapi.service.PautaService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;
    
    public Pauta salvar(Pauta pauta) {
        log.info("Cadastrando pauta: {}", pauta.getDescricao());
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
