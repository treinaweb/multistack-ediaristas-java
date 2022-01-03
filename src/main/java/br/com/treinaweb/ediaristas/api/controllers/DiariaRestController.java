package br.com.treinaweb.ediaristas.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.api.dtos.requests.DiariaRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.DiariaResponse;
import br.com.treinaweb.ediaristas.api.services.ApiDiariaService;
import br.com.treinaweb.ediaristas.core.permissions.EDiaristasPermissions;

@RestController
@RequestMapping("/api/diarias")
public class DiariaRestController {

    @Autowired
    private ApiDiariaService service;

    @EDiaristasPermissions.isCliente
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public DiariaResponse cadastrar(@RequestBody @Valid DiariaRequest request) {
        return service.cadastrar(request);
    }

}
