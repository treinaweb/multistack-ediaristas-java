package br.com.treinaweb.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.api.dtos.requests.RefrehRequest;
import br.com.treinaweb.ediaristas.api.dtos.requests.TokenRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.TokenResponse;
import br.com.treinaweb.ediaristas.core.services.TokenBlackListService;
import br.com.treinaweb.ediaristas.core.services.token.adapters.TokenService;

@Service
public class ApiAuthService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenBlackListService tokenBlackListService;

    public TokenResponse autenticar(TokenRequest tokenRequest) {
        var email = tokenRequest.getEmail();
        var senha = tokenRequest.getSenha();

        var autenticacao = new UsernamePasswordAuthenticationToken(email, senha);
        authenticationManager.authenticate(autenticacao);

        var access = tokenService.gerarAccessToken(email);
        var refresh = tokenService.gerarRefreshToken(email);

        return new TokenResponse(access, refresh);
    }

    public TokenResponse reautenticar(RefrehRequest refrehRequest) {
        var token = refrehRequest.getRefresh();
        tokenBlackListService.verificarToken(token);

        var email = tokenService.getSubjectDoRefreshToken(token);

        userDetailsService.loadUserByUsername(email);

        var access = tokenService.gerarAccessToken(email);
        var refresh = tokenService.gerarRefreshToken(email);

        tokenBlackListService.colocarTokenNaBlackList(token);

        return new TokenResponse(access, refresh);
    }

}
