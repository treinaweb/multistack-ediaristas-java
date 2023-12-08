package br.com.treinaweb.ediaristas.api.controllers;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.api.dtos.requests.AvaliacaoRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.ediaristas.api.services.ApiAvaliacaoService;
import br.com.treinaweb.ediaristas.core.permissions.EDiaristasPermissions;

@RestController
@RequestMapping("/api/diarias/{id}/avaliacao")
public class AvaliacaoRestController {

    @Autowired
    private ApiAvaliacaoService service;

    @PatchMapping
    @EDiaristasPermissions.isClienteOrDiaristaDaDiaria
    public MensagemResponse avaliarDiaria(
        @RequestBody @Valid AvaliacaoRequest request, @PathVariable Long id
    ) {
        return service.avaliarDiaria(request, id);
    }

}
