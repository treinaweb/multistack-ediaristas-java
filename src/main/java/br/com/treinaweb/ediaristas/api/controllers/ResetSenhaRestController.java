package br.com.treinaweb.ediaristas.api.controllers;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.api.dtos.requests.ResetSenhaConfirmacaoRequest;
import br.com.treinaweb.ediaristas.api.dtos.requests.ResetSenhaRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.ediaristas.api.services.ApiResetSenhaService;

@RestController
@RequestMapping("/api/recuperar-senha")
public class ResetSenhaRestController {

    @Autowired
    private ApiResetSenhaService service;

    @PostMapping
    public MensagemResponse solictarResetDeSenha(@RequestBody @Valid ResetSenhaRequest request) {
        return service.solictarResetDeSenha(request);
    }

    @PostMapping("/confirm")
    public MensagemResponse confirmarResetDeSenha(@RequestBody @Valid ResetSenhaConfirmacaoRequest request) {
        return service.confirmarResetDeSenha(request);
    }

}
