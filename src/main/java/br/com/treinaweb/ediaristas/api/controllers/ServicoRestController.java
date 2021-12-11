package br.com.treinaweb.ediaristas.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.api.dtos.responses.ServicoResponse;
import br.com.treinaweb.ediaristas.api.services.ApiServicoService;

@RestController
@RequestMapping("/api/servicos")
public class ServicoRestController {

    @Autowired
    private ApiServicoService service;

    @GetMapping
    public List<ServicoResponse> buscarTodos() {
        return service.buscarTodos();
    }

}
