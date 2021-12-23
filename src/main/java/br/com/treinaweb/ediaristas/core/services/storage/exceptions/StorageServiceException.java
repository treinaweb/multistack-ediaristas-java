package br.com.treinaweb.ediaristas.core.services.storage.exceptions;

public class StorageServiceException extends RuntimeException {

    public StorageServiceException() {}

    public StorageServiceException(String message) {
        super(message);
    }

}
