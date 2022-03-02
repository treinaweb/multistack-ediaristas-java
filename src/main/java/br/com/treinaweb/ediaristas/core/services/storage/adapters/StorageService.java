package br.com.treinaweb.ediaristas.core.services.storage.adapters;

import org.springframework.web.multipart.MultipartFile;

import br.com.treinaweb.ediaristas.core.models.Foto;
import br.com.treinaweb.ediaristas.core.services.storage.exceptions.StorageServiceException;

public interface StorageService {

    Foto salvar(MultipartFile file) throws StorageServiceException;

    void apagar(String filename) throws StorageServiceException;

}
