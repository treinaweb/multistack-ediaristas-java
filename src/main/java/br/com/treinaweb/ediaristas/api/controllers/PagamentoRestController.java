package br.com.treinaweb.ediaristas.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.api.dtos.responses.PagamentoResponse;
import br.com.treinaweb.ediaristas.api.services.ApiPagamentoService;
import br.com.treinaweb.ediaristas.core.permissions.EDiaristasPermissions;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoRestController {

    @Autowired
    private ApiPagamentoService service;

    @GetMapping
    @EDiaristasPermissions.isDiarista
    public List<PagamentoResponse> listarPagamentos() {
        return service.listarPagamentos();
    }

}
