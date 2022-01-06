package br.com.treinaweb.ediaristas.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.api.assemblers.DiariaAssembler;
import br.com.treinaweb.ediaristas.api.dtos.requests.DiariaRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.DiariaResponse;
import br.com.treinaweb.ediaristas.api.services.ApiDiariaService;
import br.com.treinaweb.ediaristas.core.permissions.EDiaristasPermissions;

@RestController
@RequestMapping("/api/diarias")
public class DiariaRestController {

    @Autowired
    private ApiDiariaService service;

    @Autowired
    private DiariaAssembler assembler;

    @EDiaristasPermissions.isCliente
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public DiariaResponse cadastrar(@RequestBody @Valid DiariaRequest request) {
        var response = service.cadastrar(request);

        assembler.adicionarLinks(response);

        return response;
    }

    @EDiaristasPermissions.isDiaristaOrCliente
    @GetMapping
    public List<DiariaResponse> listarPorUsuarioLogado() {
        var response = service.listarPorUsuarioLogado();

        assembler.adicionarLinks(response);

        return response;
    }

    @GetMapping("/{id}")
    public DiariaResponse buscarPorId(@PathVariable Long id) {
        var response = service.buscarPorId(id);

        assembler.adicionarLinks(response);

        return response;
    }

}
