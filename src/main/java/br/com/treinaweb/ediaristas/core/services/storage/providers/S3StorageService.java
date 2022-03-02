package br.com.treinaweb.ediaristas.core.services.storage.providers;

import java.io.IOException;
import java.util.UUID;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.treinaweb.ediaristas.core.models.Foto;
import br.com.treinaweb.ediaristas.core.repositories.FotoRepository;
import br.com.treinaweb.ediaristas.core.services.storage.adapters.StorageService;
import br.com.treinaweb.ediaristas.core.services.storage.exceptions.StorageServiceException;

@Service
@Profile("prod")
public class S3StorageService implements StorageService {

    @Value("${br.com.treinaweb.ediaristas.s3.accessKey}")
    private String accessKey;

    @Value("${br.com.treinaweb.ediaristas.s3.secretKey}")
    private String secretKey;

    @Value("${br.com.treinaweb.ediaristas.s3.bucket}")
    private String bucket;

    @Value("${br.com.treinaweb.ediaristas.s3.region}")
    private Regions region;

    @Autowired
    private FotoRepository fotoRepository;

    @Override
    public Foto salvar(MultipartFile file) throws StorageServiceException {
        try {
            return trySalvar(file);
        } catch (IOException e) {
            throw new StorageServiceException(e.getLocalizedMessage());
        }
    }

    @Override
    public void apagar(String filename) throws StorageServiceException {
        var s3client = getS3Cliente();
        s3client.deleteObject(bucket, filename);
    }

    private Foto trySalvar(MultipartFile file) throws IOException {
        var s3client = getS3Cliente();
        criarBucketCasoNaoExista(s3client);
        var putObjectRequest = getPutObjectRequest(file);
        s3client.putObject(putObjectRequest);
        var foto = criarFoto(file, s3client, putObjectRequest);
        return fotoRepository.save(foto);
    }

    private Foto criarFoto(MultipartFile file, AmazonS3 s3client, PutObjectRequest putObjectRequest) {
        return Foto.builder()
            .filename(putObjectRequest.getKey())
            .contentLength(file.getSize())
            .contentType(file.getContentType())
            .url(s3client.getUrl(bucket, putObjectRequest.getKey()).toString())
            .build();
    }

    private PutObjectRequest getPutObjectRequest(MultipartFile file) throws IOException {
        return new PutObjectRequest(
            bucket,
            gerarFilename(file),
            file.getInputStream(),
            getObjectMetadata(file)
        ).withCannedAcl(CannedAccessControlList.PublicRead);
    }

    private ObjectMetadata getObjectMetadata(MultipartFile file) {
        var objectMetatda = new ObjectMetadata();
        objectMetatda.setContentType(file.getContentType());
        objectMetatda.setContentLength(file.getSize());
        return objectMetatda;
    }

    private String gerarFilename(MultipartFile file) {
        var originalFilename = file.getOriginalFilename();
        var ext = originalFilename.split("\\.")[1];
        return UUID.randomUUID().toString() + "." + ext;
    }

    private void criarBucketCasoNaoExista(AmazonS3 s3client) {
        if (!s3client.doesBucketExistV2(bucket)) {
            s3client.createBucket(bucket);
        }
    }

    private AmazonS3 getS3Cliente() {
        return AmazonS3ClientBuilder.standard()
            .withCredentials(getS3CredencialsProvider())
            .withRegion(region)
            .build();
    }

    private AWSCredentialsProvider getS3CredencialsProvider() {
        var s3Credencials = new BasicAWSCredentials(accessKey, secretKey);
        return new AWSStaticCredentialsProvider(s3Credencials);
    }

}
