package br.com.treinaweb.ediaristas.core.services.email.exceptions;

public class EmailServiceException extends RuntimeException {

    public EmailServiceException() {}

    public EmailServiceException(String message) {
        super(message);
    }

}
