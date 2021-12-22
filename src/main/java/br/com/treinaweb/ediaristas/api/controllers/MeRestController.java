package br.com.treinaweb.ediaristas.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.api.dtos.responses.UsuarioResponse;
import br.com.treinaweb.ediaristas.api.services.ApiMeService;

@RestController
@RequestMapping("/api/me")
public class MeRestController {

    @Autowired
    private ApiMeService service;

    @PreAuthorize("hasAnyAuthority('DIARISTA', 'CLIENTE')")
    @GetMapping
    public UsuarioResponse me() {
        return service.obterUsuarioLogado();
    }

}
