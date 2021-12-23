package br.com.treinaweb.ediaristas.core.services.token.exceptions;

public class TokenServiceException extends RuntimeException {

    public TokenServiceException() {}

    public TokenServiceException(String message) {
        super(message);
    }

}
