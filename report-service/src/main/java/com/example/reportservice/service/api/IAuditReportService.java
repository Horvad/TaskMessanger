package com.example.reportservice.service.api;

import com.example.reportservice.core.DTO.AuditSendDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IAuditReportService {
    List<AuditSendDTO> getAudits(LocalDate dateFrom, LocalDate dateTo, UUID userUuid);
}
