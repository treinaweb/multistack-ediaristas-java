package br.com.treinaweb.ediaristas.core.services.storage.providers;

import com.amazonaws.regions.Regions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.treinaweb.ediaristas.core.models.Foto;
import br.com.treinaweb.ediaristas.core.services.storage.adapters.StorageService;
import br.com.treinaweb.ediaristas.core.services.storage.exceptions.StorageServiceException;

@Service
public class S3StorageService implements StorageService {

    @Value("${br.com.treinaweb.ediaristas.s3.accessKey}")
    private String accessKey;

    @Value("${br.com.treinaweb.ediaristas.s3.secretKey}")
    private String secretKey;

    @Value("${br.com.treinaweb.ediaristas.s3.bucket}")
    private String bucket;

    @Value("${br.com.treinaweb.ediaristas.s3.region}")
    private Regions region;

    @Override
    public Foto salvar(MultipartFile file) throws StorageServiceException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void apagar(String filename) throws StorageServiceException {
        // TODO Auto-generated method stub

    }

}
