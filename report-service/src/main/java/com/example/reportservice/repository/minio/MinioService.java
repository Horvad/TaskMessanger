package com.example.reportservice.repository.minio;

import com.example.reportservice.config.properties.MinioProperties;
import com.example.reportservice.core.exception.MinioGlobalException;
import com.example.reportservice.repository.minio.api.IMinioService;
import io.minio.*;
import io.minio.http.Method;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.concurrent.TimeUnit;

@Service
public class MinioService implements IMinioService {
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;


    public MinioService(MinioClient minioClient, MinioProperties minioProperties) {
        this.minioClient = minioClient;
        this.minioProperties = minioProperties;
    }

    @Override
    public String createFile(File file, String nameFile) {
        try {
            createBucket();
        } catch (Exception e){
            throw new MinioGlobalException("Ошибка фалового хранилища");
        }
        String fileName = nameFile;
        FileInputStream inputStreamFile;
        try {
            inputStreamFile = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        saveFile(inputStreamFile,fileName);
        return fileName;
    }

    @Override
    public String getUrl(String fileName)  {
        try {
            createBucket();
            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(minioProperties.getBucket())
                            .object(fileName)
                            .expiry(3, TimeUnit.DAYS)
                            .build());
            return url;
        } catch (Exception e) {
            throw new MinioGlobalException("Ошибка файлового хранилища");
        }
    }

    private void createBucket() throws Exception {
        boolean found = this.minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(this.minioProperties.getBucket())
                .build());
        if(!found){
            this.minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(this.minioProperties.getBucket())
                    .build());
        }
    }

    private void saveFile(InputStream inputStream, String fileName){
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .stream(inputStream,inputStream.available(),-1)
                    .bucket(minioProperties.getBucket())
                    .object(fileName)
                    .build());
        } catch (Exception e) {
            throw new MinioGlobalException("Ошибка файлового хранилища");
        }
    }
}
