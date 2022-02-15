package br.com.treinaweb.ediaristas.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.repositories.DiariaRepository;

@Service
public class WebDiariaService {

    @Autowired
    private DiariaRepository diariaRepository;

    public List<Diaria> buscarDarias() {
        return diariaRepository.findAll();
    }

}
