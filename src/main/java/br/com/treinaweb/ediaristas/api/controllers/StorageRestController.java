package br.com.treinaweb.ediaristas.api.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.core.repositories.FotoRepository;
import br.com.treinaweb.ediaristas.core.services.storage.providers.LocalStorageService;

@RestController
@RequestMapping("/uploads")
public class StorageRestController {

    @Autowired
    private FotoRepository fotoRepository;

    @Autowired
    private LocalStorageService localStorageService;

    @GetMapping
    public ResponseEntity<Object> buscarFoto(@RequestParam String filename) throws IOException {
        var foto = fotoRepository.findByFilename(filename).get();
        var file = localStorageService.buscarFoto(filename);

        return ResponseEntity.ok()
            .header("Content-Type", foto.getContentType())
            .header("Content-Length", foto.getContentLength().toString())
            .body(file.getInputStream().readAllBytes());
    }

}
