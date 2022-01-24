package br.com.treinaweb.ediaristas.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.api.assemblers.OportunidadeAssembler;
import br.com.treinaweb.ediaristas.api.dtos.responses.OportunidadeResponse;
import br.com.treinaweb.ediaristas.api.services.ApiOportunidadeService;
import br.com.treinaweb.ediaristas.core.permissions.EDiaristasPermissions;

@RestController
@RequestMapping("/api/oportunidades")
public class OportunidadeRestController {

    @Autowired
    private ApiOportunidadeService service;

    @Autowired
    private OportunidadeAssembler assembler;

    @GetMapping
    @EDiaristasPermissions.isDiarista
    public List<OportunidadeResponse> buscarOportunidades() {
        var response = service.buscarOportunidades();
        assembler.adicionarLinks(response);
        return response;
    }

}
