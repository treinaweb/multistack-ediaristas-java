package br.com.treinaweb.ediaristas.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.treinaweb.ediaristas.api.assemblers.UsuarioAssembler;
import br.com.treinaweb.ediaristas.api.dtos.requests.UsuarioRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.ediaristas.api.dtos.responses.UsuarioResponse;
import br.com.treinaweb.ediaristas.api.services.ApiUsuarioService;
import br.com.treinaweb.ediaristas.core.permissions.EDiaristasPermissions;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {

    @Autowired
    private ApiUsuarioService service;

    @Autowired
    private UsuarioAssembler assembler;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UsuarioResponse cadastrar(@ModelAttribute @Valid UsuarioRequest request) {
        var response = service.cadastrar(request);

        assembler.adicionarLinks(response);

        return response;
    }

    @PostMapping("/foto")
    @EDiaristasPermissions.isDiaristaOrCliente
    public MensagemResponse atualizarFotoUsuario(
        @RequestPart("foto_usuario") MultipartFile fotoUsuario
    ) {
        return service.atualizarFotoUsuario(fotoUsuario);
    }

}
