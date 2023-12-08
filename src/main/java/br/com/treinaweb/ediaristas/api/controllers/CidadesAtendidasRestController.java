package br.com.treinaweb.ediaristas.api.controllers;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.api.dtos.requests.CidadesAtendidasRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.CidadeAtendidaResponse;
import br.com.treinaweb.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.ediaristas.api.services.ApiCidadesAtendidasService;
import br.com.treinaweb.ediaristas.core.permissions.EDiaristasPermissions;

@RestController
@RequestMapping("/api/usuarios/cidades-atendidas")
public class CidadesAtendidasRestController {

    @Autowired
    private ApiCidadesAtendidasService service;

    @GetMapping
    @EDiaristasPermissions.isDiarista
    public List<CidadeAtendidaResponse> listarCidadesAtendidas() {
        return service.listarCidadesAtendidas();
    }

    @PutMapping
    @EDiaristasPermissions.isDiarista
    public MensagemResponse atualizarCidadesAtendidas(@RequestBody @Valid CidadesAtendidasRequest request) {
        return service.atualizarCidadesAtendidas(request);
    }

}
