package br.com.treinaweb.ediaristas.core.listeners;

import javax.persistence.PreRemove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.treinaweb.ediaristas.core.models.Foto;
import br.com.treinaweb.ediaristas.core.services.storage.adapters.StorageService;

@Component
public class FotoEntityListener {

    @Autowired
    private static StorageService storageService;

    @Autowired
    public void setStorageService(StorageService storageService) {
        FotoEntityListener.storageService = storageService;
    }

    @PreRemove
    private void preRemove(Foto foto) {
        storageService.apagar(foto.getFilename());
    }

}
