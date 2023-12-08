package br.com.treinaweb.ediaristas.api.controllers;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.api.dtos.requests.PagamentoRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.ediaristas.api.services.ApiDiariaPagamentoService;
import br.com.treinaweb.ediaristas.core.permissions.EDiaristasPermissions;

@RestController
@RequestMapping("/api/diarias/{id}")
public class DiariaPagamentoRestController {

    @Autowired
    private ApiDiariaPagamentoService service;

    @EDiaristasPermissions.isClienteDaDiaria
    @PostMapping("/pagar")
    public MensagemResponse pagar(@RequestBody @Valid PagamentoRequest request, @PathVariable Long id) {
        return service.pagar(request, id);
    }

}
