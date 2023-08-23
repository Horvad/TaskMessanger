package com.example.reportservice.service;

import com.example.reportservice.core.DTO.AuditSendDTO;
import com.example.reportservice.core.enums.ReportStatus;
import com.example.reportservice.core.enums.ReportType;
import com.example.reportservice.core.exception.FileGlobalException;
import com.example.reportservice.repository.api.IFileReportEntytiRepository;
import com.example.reportservice.repository.entity.FileReportEntity;
import com.example.reportservice.service.api.IFileService;
import com.example.reportservice.service.api.ICreateReportService;
import com.example.reportservice.repository.minio.api.IMinioService;
import com.example.reportservice.service.api.IAuditReportService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class CreateReportService implements ICreateReportService {
    private final IAuditReportService auditReportService;
    private final IFileReportEntytiRepository repository;
    private final IFileService fileService;
    private final IMinioService minioService ;

    public CreateReportService(IAuditReportService auditReportService, IFileReportEntytiRepository repository, IFileService fileService, IMinioService minioService) {
        this.auditReportService = auditReportService;
        this.repository = repository;
        this.fileService = fileService;
        this.minioService = minioService;
    }

    @Override
    public void create(UUID uuidReport, LocalDate dateFrom, LocalDate dateTo, ReportType type, UUID userUuid) {
        List<AuditSendDTO> auditViewDTOS = auditReportService.getAudits(dateFrom,dateTo,userUuid);
        File file = fileService.generateReport(auditViewDTOS,uuidReport.toString()+".xml");
        try {
            minioService.createFile(file,userUuid.toString()+".xml");
            Optional<FileReportEntity> fileReportEntity = repository.findById(uuidReport);
            FileReportEntity fileReportEdit = new FileReportEntity();
            fileReportEdit.setUuid(fileReportEntity.get().getUuid());
            fileReportEdit.setStatus(ReportStatus.DONE);
            fileReportEdit.setFileName(file.getName());
            repository.save(fileReportEdit);
        } catch (RuntimeException e){
            FileReportEntity editEntity = new FileReportEntity();
            editEntity.setUuid(uuidReport);
            editEntity.setStatus(ReportStatus.ERROR);
            repository.save(editEntity);
            throw new FileGlobalException("Ошибка создания файла"+e);
        } finally {
            file.deleteOnExit();
            String str;
            str = "";
        }
    }

    @Override
    public String getUrl(UUID uuid) {
        Optional<FileReportEntity> fileReportEntity = repository.findById(uuid);
        String fileName = fileReportEntity.orElseThrow(()->new IllegalArgumentException("Файл не найден")).getFileName();
        return minioService.getUrl(fileName);
    }

    @Override
    public void saveEntity(FileReportEntity fileReportEntity) {
        repository.save(fileReportEntity);
    }
}
