package com.example.reportservice.repository.minio.api;

import java.io.File;

public interface IMinioService {
    String createFile(File file, String nameFile);
    String getUrl(String fileName);
}
