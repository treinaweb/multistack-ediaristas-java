package br.com.treinaweb.ediaristas.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.core.exceptions.TokenNaBlackListException;
import br.com.treinaweb.ediaristas.core.models.TokenBlackList;
import br.com.treinaweb.ediaristas.core.repositories.TokenBlackListRepository;

@Service
public class TokenBlackListService {

    @Autowired
    private TokenBlackListRepository repository;

    public void verificarToken(String token) {
        if (repository.existsByToken(token)) {
            throw new TokenNaBlackListException();
        }
    }

    public void colocarTokenNaBlackList(String token) {
        if (!repository.existsByToken(token)) {
            var tokenBlackList = new TokenBlackList();
            tokenBlackList.setToken(token);
            repository.save(tokenBlackList);
        }
    }

}
