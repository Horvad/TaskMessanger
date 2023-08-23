package com.example.reportservice.service.api;

import com.example.reportservice.core.enums.ReportStatus;
import com.example.reportservice.core.enums.ReportType;
import com.example.reportservice.repository.entity.FileReportEntity;

import java.time.LocalDate;
import java.util.UUID;

public interface ICreateReportService {
    void create(UUID uuidReport, LocalDate dateFrom, LocalDate dateTo, ReportType type, UUID userUuid);
    String getUrl(UUID uuid);

    void saveEntity(FileReportEntity fileReportEntity);
}
