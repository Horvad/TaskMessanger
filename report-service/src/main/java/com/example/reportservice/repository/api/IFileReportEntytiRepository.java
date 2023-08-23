package com.example.reportservice.repository.api;

import com.example.reportservice.repository.entity.FileReportEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IFileReportEntytiRepository extends CrudRepository<FileReportEntity, UUID> {
}
