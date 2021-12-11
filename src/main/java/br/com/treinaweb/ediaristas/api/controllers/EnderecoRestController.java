package br.com.treinaweb.ediaristas.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.core.services.consultaendereco.adapters.EnderecoService;
import br.com.treinaweb.ediaristas.core.services.consultaendereco.dtos.EnderecoResponse;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoRestController {

    @Autowired
    private EnderecoService service;

    @GetMapping
    public EnderecoResponse buscarEnderecoPorCep(@RequestParam(required = false) String cep) {
        return service.buscarEnderecoPorCep(cep);
    }

}
