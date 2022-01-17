package br.com.treinaweb.ediaristas.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.api.dtos.requests.EnderecoDiaristaRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.EnderecoDiaristaResponse;
import br.com.treinaweb.ediaristas.api.services.ApiEnderecoDiaristaService;
import br.com.treinaweb.ediaristas.core.permissions.EDiaristasPermissions;

@RestController
@RequestMapping("/api/usuarios/endereco")
public class EnderecoDiaristaRestController {

    @Autowired
    private ApiEnderecoDiaristaService service;

    @PutMapping
    @EDiaristasPermissions.isDiarista
    public EnderecoDiaristaResponse alterarEndereco(@RequestBody @Valid EnderecoDiaristaRequest request) {
        return service.alterarEndereco(request);
    }

    @GetMapping
    @EDiaristasPermissions.isDiarista
    public EnderecoDiaristaResponse exibirEndereco() {
        return service.exibirEndereco();
    }

}
