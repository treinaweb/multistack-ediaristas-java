package br.com.treinaweb.ediaristas.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.api.dtos.requests.RefrehRequest;
import br.com.treinaweb.ediaristas.api.dtos.requests.TokenRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.TokenResponse;
import br.com.treinaweb.ediaristas.api.services.ApiAuthService;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

    @Autowired
    private ApiAuthService service;

    @PostMapping("/token")
    public TokenResponse autenticar(@RequestBody @Valid TokenRequest tokenRequest) {
        return service.autenticar(tokenRequest);
    }

    @PostMapping("/refresh")
    public TokenResponse reautenticar(@RequestBody @Valid RefrehRequest refrehRequest) {
        return service.reautenticar(refrehRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody @Valid RefrehRequest refrehRequest) {
        service.logout(refrehRequest);
        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }

}
