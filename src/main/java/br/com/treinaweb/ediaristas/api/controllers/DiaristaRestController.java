package br.com.treinaweb.ediaristas.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.api.dtos.responses.DiaristaLocalidadesPagedResponse;
import br.com.treinaweb.ediaristas.api.dtos.responses.DisponibilidadeResponse;
import br.com.treinaweb.ediaristas.api.services.ApiDiaristaService;

@RestController
@RequestMapping("/api/diaristas")
public class DiaristaRestController {

    @Autowired
    private ApiDiaristaService service;

    @GetMapping("/localidades")
    public DiaristaLocalidadesPagedResponse buscarDiaristasPorCep(
        @RequestParam(required = false) String cep
    ) {
        return service.buscarDiaristasPorCep(cep);
    }

    @GetMapping("/disponibilidade")
    public DisponibilidadeResponse verificarDisponibilidadePorCep(
        @RequestParam(required = false) String cep
    ) {
        return service.verificarDisponibilidadePorCep(cep);
    }

}
